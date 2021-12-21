/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Patient;
import com.hlk.model.User;
import com.hlk.repository.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class PatientRepositoryImpl implements PatientRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Patient> getPatients(String kw,int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Patient> query = builder.createQuery(Patient.class);
        Root root = query.from(Patient.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Root<User> uRoot = query.from(User.class);
            Predicate p2 = builder.equal(root.get("user"), uRoot.get("id"));
            Predicate p = builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(builder.and(p,p2));
        }
        
        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public Patient getPatientById(int patientId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Patient.class, patientId);
    }

    @Override
    public boolean addOrUpdatePatient(Patient patient) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (patient.getId() > 0)
                s.update(patient);
            else
                s.save(patient);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deletePatient(int patientId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Patient p = session.get(Patient.class, patientId);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getEmailPatientByAppointmentId(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> fromUpdates = query.from(User.class);
        Join<User, Patient> patient = fromUpdates.join("patient");
        Join<Patient, Appointment> appointment = patient.join("appointments");
        List<Predicate> conditions = new ArrayList();
        conditions.add(builder.equal(appointment.get("id"), 6));
        
        TypedQuery<User> typedQuery = session.createQuery(query
                .select(fromUpdates)
                .where(conditions.toArray(new Predicate[] {})));
        List<User> list = typedQuery.getResultList();
        
        return list.get(0).getEmail();
    }

    @Override
    public List<Patient> getPatients(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Patient> query = builder.createQuery(Patient.class);
        Root root = query.from(Patient.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Root<User> uRoot = query.from(User.class);
            Predicate p2 = builder.equal(root.get("user"), uRoot.get("id"));
            Predicate p = builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(builder.and(p,p2));
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public long countPatient(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Patient.class);
        query.select(builder.count(root.get("id")));
        if(!kw.isEmpty() && kw!=null){
            Root<User> uRoot = query.from(User.class);
            Predicate p2 = builder.equal(root.get("user"), uRoot.get("id"));
            Predicate p = builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(builder.and(p,p2));
        }
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

    
}
