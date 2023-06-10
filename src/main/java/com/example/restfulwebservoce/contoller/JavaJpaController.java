package com.example.restfulwebservoce.contoller;

import com.example.restfulwebservoce.dao.UserRepository;
import com.example.restfulwebservoce.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class JavaJpaController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public EntityModel<User> retrieveAllUsers(@PathVariable int id){
        Optional<User> user = userRepository.findById(id); //optinal 반환

        if(!user.isPresent()){ //id값이 존재하지 않을때 처리
            throw  new UserNotFoundException(String.format("ID{%s} not found", id));
        }

        EntityModel<User> resource = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource; //optinal 반환
    }

    @PostMapping("/user/{id}")
    public void setUsers(@PathVariable int id){
        userRepository.save(new User(id, "test", new Date(), "pass", "909090-111111"));
    }
}
