/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Medicine;
import com.hlk.model.PrescriptionDetail;
import com.hlk.repository.MedicineRepository;
import java.util.List;
import javax.persistence.Query;
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
public class MedicineRepositoryImpl implements  MedicineRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Medicine> getMedicines(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> query = builder.createQuery(Medicine.class);
        Root root = query.from(Medicine.class);
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
    public List<Medicine> getMedicines(String kw, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> query = builder.createQuery(Medicine.class);
        Root root = query.from(Medicine.class);
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("name").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        query = query.orderBy(builder.desc(root.get("manufacturingDate")));
        Query q = session.createQuery(query);
        int max = 6;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public Medicine getMedicineById(int i) {
        return this.sessionFactory.getObject().getCurrentSession().get(Medicine.class, i);
    }

    @Override
    public boolean addOrUpdateMedicine(Medicine mdcn) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (mdcn.getId() > 0)
                s.update(mdcn);
            else
                s.save(mdcn);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean deleteMedicine(int medicineId) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Medicine p = session.get(Medicine.class, medicineId);
            session.delete(p);
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public long countMedicine(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(Medicine.class);
        query = query.select(builder.count(root.get("id")));
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.like(root.get("name").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public List<Object[]> getMedicinesByPrescription(int preId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> bQuery = builder.createQuery(Object[].class);
        Root<PrescriptionDetail> bRoot = bQuery.from(PrescriptionDetail.class);
        bQuery = bQuery.multiselect(bRoot.get("medicine"),bRoot.get("price"),bRoot.get("quantity"));
        bQuery = bQuery.where(builder.equal(bRoot.get("prescription"), preId));
        Query query1 = session.createQuery(bQuery);
        List<Object[]> result1 = query1.getResultList();

        
        return result1;
    }
}
