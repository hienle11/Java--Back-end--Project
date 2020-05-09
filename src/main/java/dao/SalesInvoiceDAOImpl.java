package dao;

import entity.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesInvoiceDAOImpl extends AbstractHibernateDAO<SalesInvoice, Long> {

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

    private void preProcess(SalesInvoice salesInvoice) {
        List<SalesInvoiceDetail> salesInvoiceDetails = salesInvoice.getSalesInvoiceDetails();
        double totalAmount = 0;
        if (salesInvoiceDetails != null && salesInvoiceDetails.size() > 0)
        {
            for (SalesInvoiceDetail salesInvoiceDetail : salesInvoiceDetails) {

                // set oder for salesInvoice detail
                salesInvoiceDetail.setSalesInvoice(salesInvoice);

                // map price for salesInvoice detail from quantity and product price
                Long productId = salesInvoiceDetail.getProduct().getId();
                Product product = sessionFactory.getCurrentSession().get(Product.class, productId);
                if (product != null) {
                    salesInvoiceDetail.setPrice(product.getPrice() * salesInvoiceDetail.getQuantity());
                    totalAmount += salesInvoiceDetail.getPrice();
                }
            }
        } else {
            salesInvoice.setSalesInvoiceDetails(null);
        }
        salesInvoice.setTotal(totalAmount);
    }
}
