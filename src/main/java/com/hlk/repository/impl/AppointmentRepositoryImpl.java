/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Patient;
import com.hlk.model.Prescription;
import com.hlk.model.User;
import com.hlk.repository.AppointmentRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
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
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;


    @Override
    public List<Appointment> getAppointments(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root root = query.from(Appointment.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("meetDate").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Appointment getAppointmentById(int i) {
        return this.sessionFactory.getObject().getCurrentSession().get(Appointment.class, i);
    }

    @Override
    public boolean addOrUpdateAppointment(Appointment a) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (a.getId() > 0) {
                s.update(a);
            } else {
                s.save(a);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteAppointment(int AppointmentId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Appointment p = session.get(Appointment.class, AppointmentId);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Object[]> getCountPatientByYear(int year) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = builder.createQuery(Object[].class);
        Root<Appointment> root = q.from(Appointment.class);
  
        q = q.multiselect(builder.function("MONTH", Integer.class, root.get("meetDate")),
                builder.countDistinct(root.get("id")));

        Predicate p1 = builder.equal(builder.function("YEAR", Integer.class, root.get("meetDate")), year);
        

        q = q.where(builder.and(p1,root.get("nurse").isNotNull()));

        q = q.groupBy(builder.function("MONTH", Integer.class, root.get("meetDate")));

        q = q.orderBy(builder.asc(builder.function("MONTH", Integer.class, root.get("meetDate"))));
        Query query = session.createQuery(q);
        return query.getResultList();

    }

    @Override
    public boolean updateNurseAppointment(int appointmentId, int nurseId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Appointment> update = builder.createCriteriaUpdate(Appointment.class);
            Root e = update.from(Appointment.class);

            // set update and where clause
            update.set("nurse", nurseId);
            update.where(builder.equal(e.get("id"), appointmentId));

            // perform update
            session.createQuery(update).executeUpdate();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientId,Date fromDate, Date toDate, int page) {
        
        
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> bQuery = builder.createQuery(Prescription.class);
        Root<Prescription> bRoot = bQuery.from(Prescription.class);
        bQuery.select(bRoot.get("appointment")).distinct(true);
        Query query1 = session.createQuery(bQuery);
    
        List<Appointment> result1 = query1.getResultList();
        
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root root = query.from(Appointment.class);
        query = query.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
       
        predicates.add(builder.isNotNull(root.get("nurse")));

        query = query.where(predicates.toArray(new Predicate[] {}));
 
        
        
        Query q = session.createQuery(query);
        
        List<Appointment> listA = q.getResultList();
        List<Appointment> result = new ArrayList<>(); //KET QUA
        listA.stream().filter(a -> (!result1.contains(a))).forEachOrdered(a -> {
            result.add(a);
        });
        
        return result;

        
    }
    
    @Override
    public List<Appointment> getAppointmentForPatient(int patientId, Date fromDate, Date toDate, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root root = query.from(Appointment.class);
        query = query.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        
        Query q = session.createQuery(query);
        
       
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
        
    }
    


    @Override
    public long countAppointmentForBook(int patientId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Appointment.class);
        query = query.select(builder.count(root.get("id")));
        Predicate p1 = builder.equal(root.get("patient"), patientId);
        Predicate p2 = builder.isNull(root.get("nurse"));
        query = query.where(builder.and(p1,p2));
        
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public List<Appointment> getAppointments(String kw,Date fromDate, Date toDate, int page) {
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
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long countAppointment(String kw,Date fromDate, Date toDate) {
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
        
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }



    @Override
    public long countAppointmentByPatient(int patientId, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> bQuery = builder.createQuery(Prescription.class);
        Root<Prescription> bRoot = bQuery.from(Prescription.class);
        bQuery.select(bRoot.get("appointment")).distinct(true);
        Query query1 = session.createQuery(bQuery);
    
        List<Appointment> result1 = query1.getResultList();
        
        CriteriaQuery<Appointment> query = builder.createQuery(Appointment.class);
        Root root = query.from(Appointment.class);
        query = query.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
       
        predicates.add(builder.isNotNull(root.get("nurse")));

        query = query.where(predicates.toArray(new Predicate[] {}));

        
        
        Query q = session.createQuery(query);
        

        List<Appointment> listA = q.getResultList();
        List<Appointment> result = new ArrayList<>(); //KET QUA
        listA.stream().filter(a -> (!result1.contains(a))).forEachOrdered(a -> {
            result.add(a);
        });
        
        return result.size();
    }

    @Override
    public long countAppointmentForPatient(int patientId, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Appointment.class);
        query = query.select(builder.count(root.get("id")));
        

        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("meetDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("meetDate"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));


        
        Query q = session.createQuery(query);

        return Long.parseLong(q.getSingleResult().toString());
    }
}
