/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Nurse;
import com.hlk.model.Patient;
import com.hlk.model.Prescription;
import com.hlk.model.User;
import com.hlk.repository.NurseRepository;
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
public class NurseRepositoryImpl implements NurseRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Nurse> getNurses(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Nurse> query = builder.createQuery(Nurse.class);
        Root root = query.from(Nurse.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("salary").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Nurse getNurseById(int nurseId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Nurse.class, nurseId);
    }

    @Override
    public boolean addOrUpdateNurse(Nurse nurse) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (nurse.getId() > 0) {
                s.update(nurse);
            } else {
                s.save(nurse);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteNurse(int nurseId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Nurse p = session.get(Nurse.class, nurseId);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Prescription> getPrescriptionForOrder(String kw, Date createdDate,int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> query = builder.createQuery(Prescription.class);
        Root root = query.from(Prescription.class);
        query = query.select(root);
        List<Predicate> predicates = new ArrayList<>();

        if (!kw.isEmpty() && kw != null) {
            Root<Appointment> aRoot = query.from(Appointment.class);
            predicates.add(builder.equal(root.get("appointment"), aRoot.get("id")));
            Root<Patient> pRoot = query.from(Patient.class);
            predicates.add(builder.equal(aRoot.get("patient"), pRoot.get("id")));
            Root<User> uRoot = query.from(User.class);
            predicates.add(builder.equal(pRoot.get("user"), uRoot.get("id")));
            predicates.add(builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw)));
        }
        
        if(createdDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), createdDate));
        }
        predicates.add(builder.equal(root.get("isPaid"), false));
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getDataForOrder(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Prescription> pRoot = q.from(Prescription.class);
        Root<Appointment> aRoot = q.from(Appointment.class);
        
        Predicate p1 = b.equal(pRoot.get("id"), id);
        
        Predicate p2 = b.equal(pRoot.get("appointment"), aRoot.get("id"));
        
        q.where(b.and(p1,p2));
        q.multiselect(aRoot.get("expense"),pRoot.get("totalPrice"),pRoot.get("id"));
        
        Query query = session.createQuery(q);
        return query.getResultList();
        
    }

    @Override
    public List<Appointment> getAppointmentForConfirm(String kw,Date fromDate, Date toDate, int page, boolean check) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root root = query.from(Appointment.class);
        query = query.select(root);
        List<Predicate> predicates = new ArrayList<>();

        if (!kw.isEmpty() && kw != null) {
            Root<Patient> pRoot = query.from(Patient.class);
            predicates.add(builder.equal(root.get("patient"), pRoot.get("id")));
            Root<User> uRoot = query.from(User.class);
            predicates.add(builder.equal(pRoot.get("user"), uRoot.get("id")));
            predicates.add(builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw)));
        }
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
        
        
        if(check == false){
            predicates.add(builder.isNull(root.get("nurse")));
        } else {
            predicates.add(builder.isNotNull(root.get("nurse")));
        }
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long CountPrescriptionForOrder(String kw, Date createdDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Prescription.class);
        query = query.select(builder.count(root.get("id")));
        List<Predicate> predicates = new ArrayList<>();

        if (!kw.isEmpty() && kw != null) {
            Root<Appointment> aRoot = query.from(Appointment.class);
            predicates.add(builder.equal(root.get("appointment"), aRoot.get("id")));
            Root<Patient> pRoot = query.from(Patient.class);
            predicates.add(builder.equal(aRoot.get("patient"), pRoot.get("id")));
            Root<User> uRoot = query.from(User.class);
            predicates.add(builder.equal(pRoot.get("user"), uRoot.get("id")));
            predicates.add(builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw)));
        }
        
        if(createdDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdDate"), createdDate));
        }
        predicates.add(builder.equal(root.get("isPaid"), false));
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public long countAppointmentForConfirm(String kw, Date fromDate, Date toDate, boolean check) {
         Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Appointment.class);
        query = query.select(builder.count(root.get("id")));
        List<Predicate> predicates = new ArrayList<>();

        if (!kw.isEmpty() && kw != null) {
            Root<Patient> pRoot = query.from(Patient.class);
            predicates.add(builder.equal(root.get("patient"), pRoot.get("id")));
            Root<User> uRoot = query.from(User.class);
            predicates.add(builder.equal(pRoot.get("user"), uRoot.get("id")));
            predicates.add(builder.like(uRoot.get("firstName").as(String.class),
                    String.format("%%%s%%", kw)));
        }
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
        
        
        if(check == false){
            predicates.add(builder.isNull(root.get("nurse")));
        } else {
            predicates.add(builder.isNotNull(root.get("nurse")));
        }
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

   
}
