/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repositories.impl;

import com.ntq.pojo.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ntq.repositories.RouteRepository;

/**
 *
 * @author ACER
 */
@Repository
@Transactional
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Route> q = b.createQuery(Route.class);
        Root root = q.from(Route.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();

//        String kw = params.get("kw");
//        if (kw != null && !kw.isEmpty()) {
//            predicates.add(b.like(root.get("startLocation"), String.format("%%%s%%", kw)));
//        }
//
//        String cateId = params.get("cateId");
//        if (cateId != null && !cateId.isEmpty()) {
//            predicates.add(b.equal(root.get("category").as(Integer.class), Integer.parseInt(cateId)));
//        }
//
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);
        List<Route> routes = query.getResultList();

        return routes;
    }

    @Override
    public void addOrUpdate(Route r) {
        Session s = this.factoryBean.getObject().getCurrentSession();
//        if (r.getRouteID() > 0) {
//            s.update(r);
//        } else {
//            s.save(r);
//        }
          s.saveOrUpdate(r);
    }

    @Override
    public void deleteRoute(int routeID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Route r = this.getRouteById(routeID);
        s.delete(r);
    }

    @Override
    public Route getRouteById(int routeID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Route.class, routeID);
    }

    @Override
    public List<Route> getRoutes() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Route.findAll");

        return q.getResultList();
    }

}
