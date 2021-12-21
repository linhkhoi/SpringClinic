/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hlk.repository.impl;

import com.hlk.model.User;
import com.hlk.repository.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MSI GE66
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.equal(root.get("username").as(String.class), kw.trim());
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public User getUserById(int id) {
        return this.sessionFactory.getObject().getCurrentSession().get(User.class, id);
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (user.getId() > 0) {
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                s.update(user);
            } else {
                user.setPassword(this.passwordEncoder.encode(user.getPassword()));
                if(user.getUserRole()== null)
                {
                    user.setUserRole(User.ROLE_PATIENT);
                }
                user.setDateJoined(new Date());
                user.setIsActive(true);
                s.save(user);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            Session session = this.sessionFactory.getObject().getCurrentSession();
            User p = session.get(User.class, id);
            session.delete(p);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);
        Predicate p = builder.equal(root.get("username").as(String.class), username.trim());
        query = query.where(p);

        Query q = session.createQuery(query);
        List<User> listU = q.getResultList();
        if(listU.isEmpty()){
            return null;
        }
        return (User) listU.get(0);
    }

    @Override
    public List<User> getUsers(String kw, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query = query.select(root);

        if (!kw.isEmpty() && kw != null) {
            Predicate p = builder.equal(root.get("username").as(String.class), kw.trim());
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        int max = 12;
        q.setMaxResults(max);
        q.setFirstResult((page-1)*max);
        return q.getResultList();
    }

    @Override
    public long countUser(String kw) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root root = query.from(User.class);
        query.select(builder.count(root.get("id")));
        if(!kw.isEmpty() && kw!=null){
            Predicate p = builder.equal(root.get("username").as(String.class), kw.trim());
            query = query.where(p);
        }
        Query q = session.createQuery(query);
        return Long.parseLong(q.getSingleResult().toString());
    }

}
