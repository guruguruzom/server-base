package com.example.restfulwebservoce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping(path = "/test")
    public String test(){

        return "test";
    }
}
