/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.ScheduleNurse;
import com.hlk.repository.ScheduleNurseRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ScheduleNurseRepositoryImpl implements ScheduleNurseRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<ScheduleNurse> getScheduleNurses(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleNurse> query = builder.createQuery(ScheduleNurse.class);
        Root root = query.from(ScheduleNurse.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("position").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public ScheduleNurse getScheduleNurseById(int Id) {
        return this.sessionFactory.getObject().getCurrentSession().get(ScheduleNurse.class, Id);
    }

    @Override
    public boolean deleteScheduleNurse(int Id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            ScheduleNurse p = session.get(ScheduleNurse.class, Id);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addOrUpdateScheduleNurse(ScheduleNurse scheduleNurse) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (scheduleNurse.getId() > 0) {
                s.update(scheduleNurse);
            } else {
                s.save(scheduleNurse);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<ScheduleNurse> getScheduleNurseByNurse(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleNurse> query = builder.createQuery(ScheduleNurse.class);
        Root root = query.from(ScheduleNurse.class);
        query = query.select(root);
        
        query = query.where(builder.equal(root.get("nurse"), id));
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ScheduleNurse> getScheduleNurseByNurse(int id, Date fromDate, Date toDate, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<ScheduleNurse> query = builder.createQuery(ScheduleNurse.class);
        Root root = query.from(ScheduleNurse.class);
        query = query.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("nurse"), id));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("schedule"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("schedule"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        
        Query q = session.createQuery(query);
        
       
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long countSchedule(int id, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(ScheduleNurse.class);
        query = query.select(builder.count(root.get("id")));
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("nurse"), id));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("schedule"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("schedule"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        
        Query q = session.createQuery(query);
       return Long.parseLong(q.getSingleResult().toString());
    }

}
