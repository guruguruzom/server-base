package com.example.restfulwebservoce.contoller;

import com.example.restfulwebservoce.domain.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @GetMapping("/user/{id}")
    public MappingJacksonValue getUser(@PathVariable int id){

        User user = new User(id, "test", new Date(), "pass", "909090-111111");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/user")
    public MappingJacksonValue getUsers(){
       List<User> users = new LinkedList<>();
        users.add(new User(1, "test1", new Date(), "pass", "909090-111111"));
        users.add(new User(2, "test2", new Date(), "pass", "909090-211111"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userDto){
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") //id란 가변변수에
                .buildAndExpand(1)//1값을 넣는다
                .toUri(); //uri로 변환

        //201 create 반환
        return ResponseEntity.created(location).build();
    }


    //예외발생
    @PostMapping("/user/{id}")
    public User ExceptionHandle(@PathVariable int id, @RequestBody User userDto){
        if(id == -1){
            throw  new UserNotFoundException(String.format("ID[%s] not found", id));

        }
        return new User(id,"test", new Date(), "pass", "909090-111111");
    }
}
