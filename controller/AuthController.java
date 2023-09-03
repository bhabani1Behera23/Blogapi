package com.BlogApi.BlogApi.controller;

import com.BlogApi.BlogApi.entity.Role;
import com.BlogApi.BlogApi.entity.User;
import com.BlogApi.BlogApi.payload.JWTAuthResponse;
import com.BlogApi.BlogApi.payload.LoginDto;
import com.BlogApi.BlogApi.payload.SignUpDto;
import com.BlogApi.BlogApi.reposittory.RoleRepository;
import com.BlogApi.BlogApi.reposittory.UserRepository;
import com.BlogApi.BlogApi.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    // http://localhost:8080/api/auth/signin
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
// get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }


    @Autowired
private   RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // http://localhost:8080/api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signupdto){
        if(userRepository.  existsByUsername(signupdto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST); }

        if(userRepository.existsByEmail(signupdto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST); }
      //  create user object
        User user = new User();
        user.setName(signupdto.getName());
        user.setUsername(signupdto.getUsername());
        user.setEmail(signupdto.getEmail());
        user.setPassword(passwordEncoder.encode(signupdto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
         user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK); }
}
