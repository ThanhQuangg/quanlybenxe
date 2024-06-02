/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repositories;

import com.ntq.pojo.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public interface RouteRepository {

    List<Route> getRoutes();

    List<Route> getRoutes(Map<String, String> params);

    void addOrUpdate(Route r);

    Route getRouteById(int routeID);

    void deleteRoute(int routeID);
}
