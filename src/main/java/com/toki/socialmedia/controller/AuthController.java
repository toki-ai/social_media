package com.toki.socialmedia.controller;

import com.toki.socialmedia.dto.LoginDTO;
import com.toki.socialmedia.model.Role;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.UserRepository;
import com.toki.socialmedia.response.AuthResponse;
import com.toki.socialmedia.security.JwtProvider;
import com.toki.socialmedia.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //B2: zô AuthController để tạo jwt token
            //1. get email, password from request
            //2. check email, password đúm chưa
            //3. đúm òi thì save security context + tạo jwt token
            //4. return jwt token

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    UserDetailService userDetailService;

    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody User user) throws Exception {
        User queryUser = userRepository.findByEmail(user.getEmail());
        if(queryUser != null){
            throw new Exception("User is exits");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setRole(Role.USER);

        User saveUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());
        String token = jwtProvider.generateToken(authentication);
        AuthResponse authRespone = new AuthResponse(token, "Sign in successful");

        return authRespone;
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginDTO loginDto) throws Exception {
        User queryUser = userRepository.findByEmail(loginDto.getEmail());
        if(queryUser == null){
            throw new Exception("User not found");
        }
        Authentication authentication =
                authentication(loginDto.getEmail(), loginDto.getPassword());
        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token, "Sign in successful");
        return authResponse;
    }

    private Authentication authentication(String emai, String password) throws Exception {
        UserDetails userDetails = userDetailService.loadUserByUsername(emai);
        if(userDetails == null){
            throw new BadCredentialsException("User not found");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Password is incorrect");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        return authentication;
    }
}
