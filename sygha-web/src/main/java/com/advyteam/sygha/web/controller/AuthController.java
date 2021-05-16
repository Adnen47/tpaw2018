package com.advyteam.sygha.web.controller;


import static org.springframework.http.ResponseEntity.ok;

import com.advyteam.sygha.entity.User;
import com.advyteam.sygha.repository.UserRepository;
import com.advyteam.sygha.service.SyghaUserDetailsService;
import com.advyteam.sygha.web.config.TokenProvider;
import com.advyteam.sygha.web.dto.UserDTO;
import com.advyteam.sygha.web.wrapper.AuthWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    private SyghaUserDetailsService userService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthWrapper data) {
        try {
            String username = data.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            User userConnecte = this.users.findByEmail(username);
            String token = jwtTokenProvider.createToken(username, userConnecte.getRoles());
            List<String> roles = new ArrayList<>();
            userConnecte.getRoles().stream().forEach(role ->  {
                roles.add(role.getCode());
            });
            return ok(new UserDTO(username,userConnecte.getFullname(),token,roles));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        }
        userService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }

    @RequestMapping("/user")
    public ResponseEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userConnecte = this.users.findByEmail(currentPrincipalName);
        List<String> roles = new ArrayList<>();
        userConnecte.getRoles().stream().forEach(role ->  {
            roles.add(role.getCode());
        });
        return ok(new UserDTO(currentPrincipalName,userConnecte.getFullname(),"",roles));
       // List<Establishment> establishments = establishmentService.getAllEstablishment();
       // return ResponseEntity.accepted().body(establishments);
    }
}
