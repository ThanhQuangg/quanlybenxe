/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repositories.impl;

import com.ntq.pojo.Company;
import com.ntq.pojo.Route;

import com.ntq.repositories.CompanyRepository;
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

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<Company> getCompanieses(Map<String, String> params) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Company> q = b.createQuery(Company.class);
        Root root = q.from(Company.class);
        q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        q.where(predicates.toArray(Predicate[]::new));

        Query query = session.createQuery(q);
        List<Company> companies = query.getResultList();

        return companies;
    }

    @Override
    public void addOrUpdate(Company c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
//        if (c.getCompanyID() > 0) {
//            s.update(c);
//        } else {
//            s.save(c);
//        }
        s.saveOrUpdate(c);
    }

    @Override
    public Company getCompaniesByID(int companyID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Company.class, companyID);
    }

    @Override
    public void deleteCompany(int companyID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Company c = this.getCompaniesByID(companyID);
        s.delete(c);
    }

}
