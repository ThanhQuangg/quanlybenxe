//package com.ntq.repositories.impl;
//
//import com.ntq.pojo.Route;
//import com.ntq.repositories.StatsRepository;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//
//public class StatsRepositoryImpl implements StatsRepository{
//    @Autowired
//    private LocalSessionFactoryBean factory;
//    
//    @Override
//    public List<Object[]> statsRevenueByRoute() {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root rR = q.from(Route.class);
////        Root rD = q.from(OrderDetail.class);
//
//        q.multiselect(rR.get("RouteID"), rR.get("name"), b.sum(b.prod(rD.get("quantity"), rD.get("unitPrice"))));
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rD.get("productId"), rP.get("id")));
//
//        q.where(predicates.toArray(Predicate[]::new));
//
//        q.groupBy(rP.get("id"));
//
//        Query query = s.createQuery(q);
//        
//        return query.getResultList();
//    }
//
//    @Override
//    public List<Object[]> statsRevenueByPeriod(int year, String period) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<Object[]> statsRevenueByMonth(int year) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<Object[]> statsRevenueByQuarter(int year) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//}
