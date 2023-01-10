package com.brownfield.app.controller;

import com.brownfield.app.entity.User;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserRegRequest userDto){
        User response = userService.saveUserService(userDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute("user") UserRegRequest userRequest, BindingResult result, Model model){
//        if(result.hasErrors()){
//            model.addAttribute("user",userRequest);
//            return "/register";
//        }
//        userService.saveUserService(userRequest);
//        return "redirect:/login";
//    }

}
