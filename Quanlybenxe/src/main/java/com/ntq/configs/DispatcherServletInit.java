package com.ntq.configs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            HibernateConfig.class,
            TilesConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebAppContextConfig.class,};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}


//@WebServlet(name = "BusServlet", urlPatterns = {"/api/buses/*"})
//public class BusServlet extends HttpServlet {
//    // Implement other methods like doGet, doPost, etc.
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String busID = request.getPathInfo().substring(1); // Extract BusID from URL
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        try {
//            conn = // Initialize your database connection here
//
//            // First delete related routes
//            String deleteRoutesSQL = "DELETE FROM route WHERE BusID = ?";
//            stmt = conn.prepareStatement(deleteRoutesSQL);
//            stmt.setString(1, busID);
//            stmt.executeUpdate();
//
//            // Then delete the bus
//            String deleteBusSQL = "DELETE FROM bus WHERE BusID = ?";
//            stmt = conn.prepareStatement(deleteBusSQL);
//            stmt.setString(1, busID);
//            int rowsDeleted = stmt.executeUpdate();
//
//            if (rowsDeleted > 0) {
//                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
//            } else {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
//            }
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR
//
