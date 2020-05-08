package dao;

import entity.ReceivingNote;
import entity.ReceivingNoteDetail;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("receivingNoteDAO")
public class ReceivingNoteDAO extends AbstractHibernateDAO<ReceivingNote, Long> {

    @Override
    public ReceivingNote create(ReceivingNote receivingNote) {
        for(ReceivingNoteDetail noteDetail: receivingNote.getReceivingNoteDetails()) {
            noteDetail.setReceivingNote(receivingNote);
        }
        return super.create(receivingNote);
    }
}
