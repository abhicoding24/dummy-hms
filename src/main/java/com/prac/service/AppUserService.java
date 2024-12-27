package com.prac.service;

import com.prac.entity.AppUser;
import com.prac.payload.LoginDto;
import com.prac.payload.TokenDto;
import com.prac.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService{
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private JWTService jwtService;
    public String signUp(AppUser appUser){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(appUser.getUsername());
        if(opUsername.isPresent()){
            return "already have an accuont";
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(appUser.getEmail());
        if(opEmail.isPresent()){
            return "already have an accuont";
        }
        String password = BCrypt.hashpw(appUser.getPassword(),BCrypt.gensalt(5));
        appUser.setPassword(password);
        appUser.setRole("ROLE_OWNER");
        appUserRepository.save(appUser);
        return "created an account successfully";
    }
    public TokenDto loginWithUsername(LoginDto loginDto){
        Optional<AppUser> opUsername = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUsername.isPresent()){
            AppUser appUser = opUsername.get();
            BCrypt.checkpw(appUser.getPassword(),loginDto.getPassword());
            String token = jwtService.generateToken(appUser.getUsername());
            if(token!=null){
                TokenDto tokenDto = new TokenDto();
                tokenDto.setToken(token);
                tokenDto.setType("JWT");
                return tokenDto;
            }
            return null;
        }
    return null;
    }
    public TokenDto loginWithMobile(String phoneNo){
        Optional<AppUser> opUsername = appUserRepository.findBymobile(phoneNo);
        if(opUsername.isPresent()){
            AppUser appUser = opUsername.get();
            if(phoneNo.equals(appUser.getMobile())){
                String token = jwtService.generateToken(appUser.getUsername());
                if(token!=null){
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setToken(token);
                    tokenDto.setType("JWT");
                    return tokenDto;
                }
                return null;
            }
            return null;
        }
        return null;
    }
}