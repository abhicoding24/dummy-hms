package com.prac.controller;

import com.prac.entity.AppUser;
import com.prac.payload.LoginDto;
import com.prac.payload.TokenDto;
import com.prac.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appUser")
public class AppUserController{
    @Autowired
    private AppUserService appUserService;
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(AppUser appUser){
        String msg = appUserService.signUp(appUser);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<TokenDto> loginWithUsername(LoginDto loginDto){
        TokenDto dto = appUserService.loginWithUsername(loginDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

}