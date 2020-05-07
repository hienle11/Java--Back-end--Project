package dao;

import entity.ReceivingNoteDetail;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class ReceivingNoteDetailDAO
        extends AbstractHibernateDAO<ReceivingNoteDetail, Long>
        implements GenericDAO<ReceivingNoteDetail, Long> {

}
