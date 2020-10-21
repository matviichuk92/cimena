package com.cinema.dao.impl;

import com.cinema.dao.ShoppingCartDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartImpl.class);
    private final SessionFactory sessionFactory;

    public ShoppingCartImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            logger.info("Successfully added " + shoppingCart + " to DB");
            return shoppingCart;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert " + shoppingCart, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> query = builder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = query.from(ShoppingCart.class);
            Predicate byUserId = builder.equal(root.get("id"), user.getId());
            root.fetch("tickets", JoinType.LEFT);
            query.select(root).where(byUserId);
            return session.createQuery(query).getSingleResult();
        } catch (Exception exception) {
            throw new DataProcessingException("Can't get cart by user id"
                    + user.getId(), exception);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            logger.info("Successfully updated the shopping cart " + shoppingCart);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update " + shoppingCart, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
