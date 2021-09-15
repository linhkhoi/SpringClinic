/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.ScheduleDoctor;
import com.hlk.repository.ScheduleDoctorRepository;
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
public class ScheduleDoctorRepositoryImpl implements ScheduleDoctorRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<ScheduleDoctor> getScheduleDoctors(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDoctor> query = builder.createQuery(ScheduleDoctor.class);
        Root root = query.from(ScheduleDoctor.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("position").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public ScheduleDoctor getScheduleDoctorById(int Id) {
        return this.sessionFactory.getObject().getCurrentSession().get(ScheduleDoctor.class, Id);
    }

    @Override
    public boolean deleteScheduleDoctor(int Id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            ScheduleDoctor p = session.get(ScheduleDoctor.class, Id);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean addOrUpdateScheduleDoctor(ScheduleDoctor scheduleDoctor) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (scheduleDoctor.getId() > 0)
                s.update(scheduleDoctor);
            else
                s.save(scheduleDoctor);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
}
