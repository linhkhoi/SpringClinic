/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Order;
import com.hlk.repository.OrderRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MSI GE66
 */
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Order> getOrders(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        Root root = query.from(Order.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("createdDate").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Order getOrderById(int orderId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Order.class, orderId);
    }

    @Override
    public boolean addOrUpdateOrder(Order order) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (order.getId() > 0) {
                s.update(order);
            } else {
                s.save(order);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Order p = session.get(Order.class, orderId);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Object[]> getCountSalesByMonth(int month, int year) {


        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q
                = builder.createQuery(Object[].class);
        Root<Order> root = q.from(Order.class);

        q.multiselect(builder.function("DAY", Integer.class, root.get("createdDate")),
                builder.sum(root.<BigDecimal>get("totalPriceOrder")));

        Predicate p1 = builder.equal(builder.function("MONTH", Integer.class, root.get("createdDate")), month);
        Predicate p2 = builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), year);
        q.where(builder.and(p1,p2));
        q.groupBy(builder.function("DAY", Integer.class, root.get("createdDate")));

        q.orderBy(builder.asc(builder.function("DAY", Integer.class, root.get("createdDate"))));

        Query query = session.createQuery(q);
        return query.getResultList();


    }

    @Override
    public List<Object[]> getCountSalesByQuarter(int quarter, int year) {


        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q
                = builder.createQuery(Object[].class);
        Root<Order> root = q.from(Order.class);

        q.multiselect(builder.function("MONTH", Integer.class, root.get("createdDate")),
                builder.sum(root.<BigDecimal>get("totalPriceOrder")));

        
        Predicate p1 = builder.equal(builder.function("QUARTER", Integer.class, root.get("createdDate")), quarter);
        Predicate p2 = builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), year);
        q.where(builder.and(p1,p2));

        q.groupBy(builder.function("MONTH", Integer.class, root.get("createdDate")));

        q.orderBy(builder.asc(builder.function("MONTH", Integer.class, root.get("createdDate"))));

        Query query = session.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<Object[]> getCountSalesByYear(int year) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q
                = builder.createQuery(Object[].class);
        Root<Order> root = q.from(Order.class);

        q.multiselect(builder.function("MONTH", Integer.class, root.get("createdDate")),
                builder.sum(root.<BigDecimal>get("totalPriceOrder")));

        q.where(builder.equal(builder.function("YEAR", Integer.class, root.get("createdDate")), year));

        q.groupBy(builder.function("MONTH", Integer.class, root.get("createdDate")));

        q.orderBy(builder.asc(builder.function("MONTH", Integer.class, root.get("createdDate"))));

        Query query = session.createQuery(q);
        return query.getResultList();

    }

}
