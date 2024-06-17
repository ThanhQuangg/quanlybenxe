package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.pojo.Company;
import com.ntq.pojo.Route;
import com.ntq.pojo.Trip;
import com.ntq.services.BusService;
import com.ntq.services.CompanyService;
import com.ntq.services.RouteService;
import com.ntq.services.TripService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ApiTripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private BusService busService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/trips")
    @CrossOrigin
    public ResponseEntity<List<Trip>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.tripService.getTrips(params), HttpStatus.OK);
    }

    @GetMapping(path = "/trips/{tripID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> retrieve(@PathVariable(value = "tripID") int tripID) {
        return new ResponseEntity<>(this.tripService.getTripById(tripID), HttpStatus.OK);
    }

    @PostMapping(path = "/trips", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void createTrip(@RequestParam Map<String, String> params) {
        Trip trip = new Trip();
        trip.setName(params.get("name"));
        // Chuyển đổi chuỗi thời gian thành Date
        String departureTimeStr = params.get("departureTime");
        if (departureTimeStr != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date departureTime = dateFormat.parse(departureTimeStr);
                trip.setDepartureTime(departureTime);
            } catch (ParseException e) {
                System.out.println("Định dạng thời gian không đúng.");
            }
        }
        // Chuyển đổi chuỗi thời gian thành Date
        String arrivalTimeStr = params.get("arrivalTime");
        if (arrivalTimeStr != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date arrivalTime = dateFormat.parse(arrivalTimeStr);
                trip.setArrivalTime(arrivalTime);
            } catch (ParseException e) {
                System.out.println("Định dạng thời gian không đúng.");
            }
        }
        // Sử dụng BigDecimal để chuyển đổi từ String
        String ticketPriceStr = params.get("ticketPrice");
        if (ticketPriceStr != null) {
            try {
                BigDecimal ticketPrice = new BigDecimal(ticketPriceStr);
                trip.setTicketPrice(ticketPrice);
            } catch (NumberFormatException e) {
                System.out.println("Giá vé không hợp lệ.");
            }
        }
        trip.setStatus(params.get("status"));
        trip.setCreatedAt(new Date());

        // Chuyển đổi busID từ chuỗi sang đối tượng Bus
        int busId = Integer.parseInt(params.get("busID"));
        Bus bus = busService.getBusesById(busId);
        trip.setBusID(bus);

        // Chuyển đổi routeID từ chuỗi sang đối tượng Route
        int routeId = Integer.parseInt(params.get("routeID"));
        Route route = routeService.getRoutesById(routeId);
        trip.setRouteID(route);
        
        // Chuyển đổi companyID từ chuỗi sang đối tượng Route
        int companyId = Integer.parseInt(params.get("companyID"));
        Company company = companyService.getCompaniesById(companyId);
        trip.setCompanyID(company);

        this.tripService.addOrUpdate(trip);
    }

    @DeleteMapping("/trips/{tripID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "tripID") int tripID) {
        this.tripService.deleteTrip(tripID);
    }
}
