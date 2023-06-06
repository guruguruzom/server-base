package com.example.restfulwebservoce.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserV2Controller {


    //@GetMapping(value = "/v1/user/{id}/" //쓰던거
    //@GetMapping(value = "/user/{id}/", params = "version=1")//param을 통한 버전관리
    //@GetMapping(value = "/user/{id}/", headers = "X-API-VERSION=1")//header를 통한 버전관리
    @GetMapping(value = "/user/{id}/", produces = "application/vnd.company.appv1+json")//MIME 타입 을 통한 버전관리
    // NOTE:이전엔 메일을 통한 방식이였으나 최근엔 웹으로도 많이 사용, header에 Accept key값 추가 후 사용
    public String getUser(){

        return "";
    }
}
