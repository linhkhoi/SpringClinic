/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.Appointment;
import com.hlk.model.Cart;
import com.hlk.model.Patient;
import com.hlk.model.Prescription;
import com.hlk.model.PrescriptionDetail;
import com.hlk.model.User;
import com.hlk.repository.PrescriptionRepository;
import com.hlk.service.AppointmentService;
import com.hlk.service.DoctorService;
import com.hlk.service.MedicineService;
import com.hlk.utils.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MSI GE66
 */
@Repository
@Transactional
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private MedicineService medicineService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private DoctorService doctorService;

    @Override
    public List<Prescription> getPrescriptions(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> query = builder.createQuery(Prescription.class);
        Root root = query.from(Prescription.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.like(root.get("createdDate").as(String.class),
                    String.format("%%%s%%", kw));
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Prescription getPrescriptionById(int id) {
        return this.sessionFactory.getObject().getCurrentSession().get(Prescription.class, id);
    }

    @Override
    public boolean addOrUpdatePrescription(Prescription prescription) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (prescription.getId() > 0) {
                s.update(prescription);
            } else {
                s.save(prescription);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePrescription(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            Prescription p = session.get(Prescription.class, id);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateIsPaid(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Prescription> update = builder.createCriteriaUpdate(Prescription.class);
            Root e = update.from(Prescription.class);
 
            // set update and where clause
            update.set("isPaid", true);
            update.where(builder.equal(e.get("id"), id));

            // perform update
            session.createQuery(update).executeUpdate();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addPrescription(Map<Integer, Cart> cart, int appointmentId, int doctorId) {
        if (cart == null) {
            return false;
        }

        Session session = this.sessionFactory.getObject().getCurrentSession();

        Map<String, BigDecimal> stats = Utils.cartStats(cart);

        try {
            Prescription pre = new Prescription();
            pre.setCreatedDate(new Date());
            pre.setTotalPrice(stats.get("amount"));
            pre.setAppointment(this.appointmentService.getAppointmentById(appointmentId));
            pre.setDoctor(this.doctorService.getDoctorById(doctorId));
            session.save(pre);
            
            cart.values().stream().map(c -> {
                PrescriptionDetail d = new PrescriptionDetail();
                d.setPrescription(pre);
                d.setQuantity(c.getQuantity());
                d.setPrice(c.getPrice());
                d.setMedicine(this.medicineService.getMedicineById(c.getMedicineId()));
                return d;
            }).forEachOrdered(d -> {
                session.save(d);
            });
            
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Prescription> getPrescriptions(String kw, Date createdDate, int page) {
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
        
        query = query.where(predicates.toArray(new Predicate[] {}));

        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long countPrescription(String kw, Date createdDate) {
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
        
        query = query.where(predicates.toArray(new Predicate[] {}));
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

    

}
