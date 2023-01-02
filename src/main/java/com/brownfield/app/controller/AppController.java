package com.brownfield.app.controller;

import com.brownfield.app.request.FlightSearchRequest;
import com.brownfield.app.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private UserRequest userDto;
    @Autowired
    private FlightSearchRequest flightDto;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("flight",flightDto);
        return "search";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",userDto);
        return "register";
    }



}
