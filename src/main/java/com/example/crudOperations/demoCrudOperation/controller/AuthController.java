package com.example.crudOperations.demoCrudOperation.controller;

import com.example.crudOperations.demoCrudOperation.model.Users;
import com.example.crudOperations.demoCrudOperation.repo.UsersRepo;
import com.example.crudOperations.demoCrudOperation.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UsersService usersService;
@PostMapping("/signin")
    public  Users Users (@RequestBody Users users){
return usersService.addusers(users);

    }
@PostMapping("/login")
public String login(@RequestBody Users users)
{
    System.out.println(users);
   return usersService.verifyUser(users);
}
}
