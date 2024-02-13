package it.bootcamp.proyectoecommerce.controller;

import it.bootcamp.proyectoecommerce.dto.request.UserRequest;
import it.bootcamp.proyectoecommerce.dto.response.UserResponse;
import it.bootcamp.proyectoecommerce.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/user")
public class UserController {

    private IService<UserRequest, UserResponse, Long> userService;

    public UserController(IService<UserRequest, UserResponse, Long> userService){
        this.userService = userService;
    }
    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getUser(){
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id){
        return new ResponseEntity<>( this.userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user){
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        this.userService.delete(id);
        return new ResponseEntity<>("Todo ok!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody UserRequest user){
        return new ResponseEntity<>(this.userService.update(user, id), HttpStatus.OK);
    }

}
