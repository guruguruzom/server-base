package com.example.restfulwebservoce.contoller;

import com.example.restfulwebservoce.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;

@RestController
public class UserController {


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){

        return new User(id,"test", new Date(), "pass", "909090-111111");
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
