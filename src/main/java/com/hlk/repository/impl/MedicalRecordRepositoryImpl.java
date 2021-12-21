/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.MedicalRecord;
import com.hlk.repository.MedicalRecordRepository;
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
 * @author MSIGE66
 */
@Repository
@Transactional
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<MedicalRecord> getMedicalRecords(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
        Root root = query.from(MedicalRecord.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("startDate").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public MedicalRecord getMedicalRecordById(int medicalRecordId) {
        return this.sessionFactory.getObject().getCurrentSession().get(MedicalRecord.class, medicalRecordId);
    }

    @Override
    public boolean addOrUpdateMedicalRecord(MedicalRecord medicalRecord) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (medicalRecord.getId() > 0)
                s.update(medicalRecord);
            else
                s.save(medicalRecord);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deleteMedicalRecord(int medicalRecordId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            MedicalRecord p = session.get(MedicalRecord.class, medicalRecordId);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatient(int patientId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
        Root root = query.from(MedicalRecord.class);
        query = query.select(root);
        query = query.where(builder.equal(root.get("patient"), patientId));
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatient(int patientId, Date fromDate, Date toDate, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<MedicalRecord> query = builder.createQuery(MedicalRecord.class);
        Root root = query.from(MedicalRecord.class);
        query = query.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("startDate"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        
        Query q = session.createQuery(query);
        
       
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long countMedicalRecord(int patientId, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(MedicalRecord.class);
        query = query.select(builder.count(root.get("id")));
        

        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(root.get("patient"), patientId));
    
        
        if(fromDate != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("startDate"), fromDate));
        }
        
        if(toDate != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("startDate"), toDate));
        }
        
        
        query = query.where(predicates.toArray(new Predicate[] {}));


        
        Query q = session.createQuery(query);

        return Long.parseLong(q.getSingleResult().toString());
    }
    
}
