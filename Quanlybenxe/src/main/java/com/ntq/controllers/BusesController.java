package com.ntq.controllers;

import com.ntq.pojo.Bus;
import com.ntq.services.BusService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusesController {

    @Autowired
    private BusService busService;

    @GetMapping("/buses")
    public String createView(Model model) {

        model.addAttribute("bus", new Bus());
        return "buses";
    }
//    @GetMapping
//    public String listBuses(Model model, Map<String, String> params) {
//        model.addAttribute("buses", busService.getBuses(params));
//        return "buses/list";
//    }
//    @GetMapping("/buses")
//    public List<Bus> getBuses(Map<String, String> params) {
//        return busService.getBuses(params);
//    }
    
    @PostMapping("/buses")
    public String createBus(@ModelAttribute(value = "bus") @Valid Bus b,
            BindingResult rs) {
        
        if (!rs.hasErrors()) {
            try {
                this.busService.addOrUpdate(b);
                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "buses";
    }

    @GetMapping("/buses/{busID}")
    public String updateView(Model model, @PathVariable(value = "busID") int busID) {
        model.addAttribute("bus", this.busService.getBusesById(busID));

        return "buses";
    }

}
