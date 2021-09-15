/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Prescription;
import com.hlk.repository.AppointmentRepository;
import com.hlk.service.PatientService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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

    @Autowired
    private PatientService patientService;

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
        CriteriaQuery<Object[]> q
                = builder.createQuery(Object[].class);
        Root<Appointment> root = q.from(Appointment.class);

        q.multiselect(builder.function("MONTH", Integer.class, root.get("meetDate")),
                builder.countDistinct(root.get("patient")));

        Predicate p1 = builder.equal(builder.function("YEAR", Integer.class, root.get("meetDate")), year);
        Predicate p2 = builder.notEqual(root.get("nurse"), null);

        q.where(builder.equal(builder.function("YEAR", Integer.class, root.get("meetDate")), year));

        q.groupBy(builder.function("MONTH", Integer.class, root.get("meetDate")));

        q.orderBy(builder.asc(builder.function("MONTH", Integer.class, root.get("meetDate"))));

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
    public List<Appointment> getAppointmentByPatient(int patientId) {
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
        Predicate p1 = builder.equal(root.get("patient"), this.patientService.getPatientById(patientId));
        Predicate p2 = builder.isNotNull(root.get("nurse"));
        query = query.where(builder.and(p1,p2));
        
        Query q = session.createQuery(query);
        List<Appointment> listA = q.getResultList();
        List<Appointment> result = new ArrayList<Appointment>();
        for(Appointment a : listA){
            if(!result1.contains(a)){
                result.add(a);
            } 
        }
        
        
        return result;

        
    }
}
