package com.example.restfulwebservoce.contoller;

import com.example.restfulwebservoce.dao.PostRepository;
import com.example.restfulwebservoce.dao.UserRepository;
import com.example.restfulwebservoce.domain.Post;
import com.example.restfulwebservoce.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class JavaJpaController {
    //@Autowired
    private final UserRepository userRepository;

    //@Autowired
    private final PostRepository postRepository;

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
        //userRepository.save(new User(id, "test", new Date(), "pass", "909090-111111"));
    }

    @PostMapping("/user/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){ //id값이 존재하지 않을때 처리
            throw  new UserNotFoundException(String.format("ID{%s} not found", id));
        }

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
        //userRepository.save(new User(id, "test", new Date(), "pass", "909090-111111"));
    }
    @GetMapping("/user/{id}/posts")
    public List<Post> retrieveAllPostsByUsers(@PathVariable int id){
        Optional<User> user = userRepository.findById(id); //optinal 반환

        if(!user.isPresent()){ //id값이 존재하지 않을때 처리
            throw  new UserNotFoundException(String.format("ID{%s} not found", id));
        }


        return user.get().getPosts(); //optinal 반환
    }
}
