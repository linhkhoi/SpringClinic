/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Doctor;
import com.hlk.model.MedicalRecord;
import com.hlk.model.Prescription;
import com.hlk.model.Sick;
import com.hlk.repository.DoctorRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
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
public class DoctorRepositoryImpl implements DoctorRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;


    @Override
    public List<Doctor> getDoctors(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = builder.createQuery(Doctor.class);
        Root root = query.from(Doctor.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("salary").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        return this.sessionFactory.getObject().getCurrentSession().get(Doctor.class, doctorId);
    }

    @Override
    public boolean addOrUpdateDoctor(Doctor doctor) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (doctor.getId() > 0)
                s.update(doctor);
            else
                s.save(doctor);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deleteDoctor(int doctorId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Doctor p = session.get(Doctor.class, doctorId);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    @Override
    public List<Object[]> getPatientListById(int patientId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();        
        CriteriaQuery<MedicalRecord> searchQuery = builder.createQuery(MedicalRecord.class);
        Root<MedicalRecord> aRoot = searchQuery.from(MedicalRecord.class);
        Join<MedicalRecord, Sick> bJoin= aRoot.join("sick");
        bJoin.on(builder.equal(aRoot.get("patient"), patientId));
        
       
        Query query = session.createQuery(searchQuery);
        return query.getResultList();
    }
    
}
