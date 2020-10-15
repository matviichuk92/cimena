package com.cinema.dao.impl;

import com.cinema.Application;
import com.cinema.dao.TicketDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.Ticket;
import com.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            Application.logger.info(ticket);
            return ticket;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + ticket, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
