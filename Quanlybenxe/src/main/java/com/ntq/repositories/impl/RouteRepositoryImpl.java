package com.ntq.repositories.impl;

import com.ntq.pojo.Route;
import com.ntq.repositories.RouteRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class RouteRepositoryImpl implements RouteRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Route> getRoutes(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Route> q = b.createQuery(Route.class);
        Root root = q.from(Route.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
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

//    @Override
//    public List<Route> getRoutes() {
//        Session s = this.factoryBean.getObject().getCurrentSession();
//        Query q = s.createNamedQuery("Route.findAll");
//
//        return q.getResultList();
//    }
}
