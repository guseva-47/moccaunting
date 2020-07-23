package com.guseva.moccaunting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping
    public Principal main(Principal principal){
        return principal;
    }
}
