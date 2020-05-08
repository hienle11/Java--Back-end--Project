package dao;

import entity.ReceivingNoteDetail;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("receivingNoteDetailDAO")
public class ReceivingNoteDetailDAOImpl extends AbstractHibernateDAO<ReceivingNoteDetail, Long> {
}
