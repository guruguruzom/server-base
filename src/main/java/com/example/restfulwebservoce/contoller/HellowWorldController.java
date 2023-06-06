package com.example.restfulwebservoce.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HellowWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path ="/hello-word-internationalized")
    public String helloWorldInterationalizedString(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);

    }
}
