/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.PrescriptionDetail;
import com.hlk.repository.PrescriptionDetailRepository;
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
public class PrescriptionDetailRepositoryImpl implements PrescriptionDetailRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<PrescriptionDetail> getPrescriptionDetails(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PrescriptionDetail> query = builder.createQuery(PrescriptionDetail.class);
        Root root = query.from(PrescriptionDetail.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("price").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public PrescriptionDetail getPrescriptionDetailById(int id) {
        return this.sessionFactory.getObject().getCurrentSession().get(PrescriptionDetail.class, id);
    }

    @Override
    public boolean addOrUpdatePrescriptionDetail(PrescriptionDetail prescriptionDetail) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (prescriptionDetail.getId() > 0)
                s.update(prescriptionDetail);
            else
                s.save(prescriptionDetail);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deletePrescriptionDetail(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            PrescriptionDetail p = session.get(PrescriptionDetail.class, id);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
