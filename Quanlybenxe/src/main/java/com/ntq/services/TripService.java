package com.ntq.services;

import com.ntq.pojo.Cart;
import com.ntq.pojo.Trip;
import java.util.List;
import java.util.Map;

public interface TripService {

    List<Trip> getTrips(Map<String, String> params);

    void addOrUpdate(Trip t);

    Trip getTripById(int tripID);

    void deleteTrip(int tripID);
    
    boolean addReceipt(Map<String, Cart> cart);
}
