package com.example.restfulwebservoce.contoller;

import com.example.restfulwebservoce.domain.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {


    @GetMapping("/user/{id}")
    public EntityModel<User> getUser(@PathVariable int id){


        User user = new User(id, "test", new Date(), "pass", "909090-111111");

        EntityModel<User> resource = new EntityModel<>(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).createUser(user));

        resource.add(linkTo.withRel("all-users"));

        return resource;
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
