    package com.ntq.repositories.impl;

import com.ntq.pojo.Cart;
import com.ntq.pojo.OrderDetails;
import com.ntq.pojo.Orders;
import com.ntq.pojo.Trip;
import com.ntq.pojo.User;
import com.ntq.repositories.ReceiptRepository;
import com.ntq.repositories.TripRepository;
import com.ntq.repositories.UserRepository;
import java.util.Date;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TripRepository tripRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {

        Session s = this.factory.getObject().getCurrentSession();
        Orders order = new Orders();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userRepo.getUserByUsername(authentication.getName());
            order.setUserId(u);
            order.setOrderDate(new Date());
            s.save(u);
   

            for (Cart c : carts.values()) {
                OrderDetails d = new OrderDetails();
                Trip trip = this.tripRepo.getTripById(c.getId());
                if (trip != null) {
                    d.setTripId(trip);
//                    d.setTripId(this.tripRepo.getTripById(c.getId()));
                    d.setOrderId(order);
                    d.setQuantity(c.getQuantity());
                    d.setPrice(c.getPrice());

                    s.save(d);
                }
            }
//            s.save(order);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
