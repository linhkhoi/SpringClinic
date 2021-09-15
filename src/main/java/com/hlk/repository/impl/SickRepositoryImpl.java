/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Sick;
import com.hlk.repository.SickRepository;
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
public class SickRepositoryImpl implements SickRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Sick> getSicks(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Sick> query = builder.createQuery(Sick.class);
        Root root = query.from(Sick.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("name").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Sick getSickById(int id) {
        return this.sessionFactory.getObject().getCurrentSession().get(Sick.class, id);
    }

    @Override
    public boolean addOrUpdateSick(Sick sick) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (sick.getId() > 0)
                s.update(sick);
            else
                s.save(sick);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deleteSick(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Sick p = session.get(Sick.class, id);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    
}
