package com.ProjetILEIC.ILIEC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}

