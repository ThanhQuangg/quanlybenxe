package com.ntq.controllers;

import com.ntq.pojo.Route;
import com.ntq.services.RouteService;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiRouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/routes")
    @CrossOrigin
    public ResponseEntity<List<Route>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.routeService.getRoutes(params), HttpStatus.OK);
    }

    @GetMapping(path = "/routes/{routeID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> retrieve(@PathVariable(value = "routeID") int routeID) {
        return new ResponseEntity<>(this.routeService.getRoutesById(routeID), HttpStatus.OK);
    }

    @PostMapping(path = "/routes", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createRoute(@RequestParam Map<String, String> params) {
        Route route = new Route();
        route.setName(params.get("name"));
        route.setStartLocation(params.get("startLocation"));
        route.setEndLocation(params.get("endLocation"));
        // Sử dụng BigDecimal để chuyển đổi từ String
        String distanceStr = params.get("distance");
        if (distanceStr != null) {
            BigDecimal distance = new BigDecimal(distanceStr);
            route.setDistance(distance);
        }
        // Thiết lập giá trị cho estimatedDuration
        String estimatedDurationStr = params.get("estimatedDuration");
        if (estimatedDurationStr != null) {
            try {
                OffsetTime estimatedDuration = OffsetTime.parse(estimatedDurationStr, DateTimeFormatter.ISO_OFFSET_TIME);
                Instant instant = estimatedDuration.atDate(LocalDate.now()).toInstant();
                Date estimatedDurationDate = Date.from(instant);
                route.setEstimatedDuration(estimatedDurationDate);
            } catch (DateTimeParseException e) {
                System.out.println("Định dạng thời gian không đúng.");
            }
        }
        route.setDescription(params.get("description"));
        route.setCreatedAt(new Date());

        this.routeService.addOrUpdate(route);
    }

    @DeleteMapping("/routes/{routeID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "routeID") int routeID) {
        this.routeService.deleteRoute(routeID);
    }
}
