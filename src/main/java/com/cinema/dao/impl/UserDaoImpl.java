package com.cinema.dao.impl;

import com.cinema.dao.UserDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.User;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("Successfully added " + user + " to DB");
            return user;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + user, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Predicate byEmail = builder.equal(root.get("email"), email);
            query.select(root).where(byEmail);
            return session.createQuery(query).uniqueResultOptional();
        } catch (Exception exception) {
            throw new DataProcessingException("Can't get user by " + email, exception);
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception exception) {
            throw new DataProcessingException("Can't get user by id : " + id, exception);
        }
    }
}
