package dao;

import entity.*;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SalesInvoiceDAOImpl extends AbstractHibernateDAO<SalesInvoice, Long> implements SalesInvoiceDAO{

    @Override
    public SalesInvoice create(SalesInvoice salesInvoice) {
        preProcess(salesInvoice);
        return super.create(salesInvoice);
    }

    @Override
    public SalesInvoice update(SalesInvoice salesInvoice) {
        preProcess(salesInvoice);
        return super.update(salesInvoice);
    }

    @Override
    public List<SalesInvoice> searchPaginated(String field, String searchKey) {
        if (field.equalsIgnoreCase("product")) {
            Query<SalesInvoiceDetail> query = sessionFactory.getCurrentSession()
                    .createQuery("from SalesInvoiceDetail "
                            + " where str(" + field + ") like '%" + searchKey + "%'");
            List<SalesInvoiceDetail> invoiceDetailResults = query.getResultList();
            Set<Long> salesInvoiceIds = new HashSet<>();
            for(SalesInvoiceDetail salesInvoiceDetail: invoiceDetailResults) {
                salesInvoiceIds.add(salesInvoiceDetail.getSalesInvoice().getId());
            }
            List<SalesInvoice> result = new ArrayList<>();
            for(Long eachId: salesInvoiceIds) {
                    result.add(findById(eachId));
            }
            return result;
        } else {
            return super.searchPaginated(field, searchKey);
        }
    }

    private void preProcess(SalesInvoice salesInvoice) {
        List<SalesInvoiceDetail> salesInvoiceDetails = salesInvoice.getSalesInvoiceDetails();
        double totalAmount = 0;
        if (salesInvoiceDetails != null && salesInvoiceDetails.size() > 0)
        {
            for (SalesInvoiceDetail salesInvoiceDetail : salesInvoiceDetails) {

                if (salesInvoiceDetail.getId() != null) { // update case

                    // get the corresponding invoice detail in the database
                    SalesInvoiceDetail currentSalesInvoiceDetail =
                            sessionFactory.getCurrentSession().get(SalesInvoiceDetail.class, salesInvoiceDetail.getId());

                    // if the quantity is not provided get it from the corresponding entity in database
                    if (salesInvoiceDetail.getQuantity() == 0) {
                        salesInvoiceDetail.setQuantity(currentSalesInvoiceDetail.getQuantity());
                    }
                }

                // if the product is not provided get it from the corresponding entity in database
                if (salesInvoiceDetail.getProduct() == null) {
                    salesInvoiceDetail.setProduct(
                            sessionFactory.getCurrentSession()
                            .get(SalesInvoiceDetail.class, salesInvoiceDetail.getId()).getProduct());
                } else { // if the product is provided, use it id to get the actual product saved in database
                    salesInvoiceDetail.setProduct(
                            sessionFactory.getCurrentSession()
                            .get(Product.class, salesInvoiceDetail.getProduct().getId())
                    );
                }

                // set oder for salesInvoice detail
                salesInvoiceDetail.setSalesInvoice(salesInvoice);

                if (salesInvoiceDetail.getPrice() == 0) {
                    salesInvoiceDetail.setPrice(salesInvoiceDetail.getProduct().getPrice() * salesInvoiceDetail.getQuantity());
                }
                totalAmount += salesInvoiceDetail.getPrice();
            }
        } else {
            salesInvoice.setSalesInvoiceDetails(null);
        }
        salesInvoice.setTotal(totalAmount);



    }

    @Override
    public List<SalesInvoice> searchByPeriod(String startDate, String endDate) {
        Query<SalesInvoice> query = sessionFactory.getCurrentSession()
                .createQuery("from SalesInvoice "
                        + " where (date >= '" + startDate + "' AND date <= '" + endDate + "')");
        return query.list();
    }

    @Override
    public List<SalesInvoice> searchByFieldAndPeriod(String startDate, String endDate, String field, String searchKey) {
        Query<SalesInvoice> query = sessionFactory.getCurrentSession()
                .createQuery("from SalesInvoice "
                        + " where (date >= '" + startDate + "' AND date <= '" + endDate + "'"
                        + " AND " + field + "=" + searchKey + ")");
        return query.list();
    }
}
