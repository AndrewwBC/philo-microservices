package com.filo.users.controller;

import com.filo.users.dtos.UserDTO;
import com.filo.users.model.UserModel;
import com.filo.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody @Valid UserDTO userDTO, @PathVariable String id){
        System.out.println(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDTO, id));
    }
}
