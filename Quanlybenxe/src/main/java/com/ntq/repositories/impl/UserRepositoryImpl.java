package com.ntq.repositories.impl;

import com.ntq.pojo.User;
import com.ntq.repositories.UserRepository;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }


    @Override
    public void addUser(User user) {
        if (user != null) {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(user);
        } else {
            throw new IllegalArgumentException("User object is null");
        }
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        if (u == null) {
            // Xử lý trường hợp không tìm thấy user
            return false;
        }
        return this.passEncoder.matches(password, u.getPassword());
    }

}
