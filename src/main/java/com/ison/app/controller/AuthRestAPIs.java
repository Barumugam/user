package com.ison.app.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.jwt.JwtProvider;
import com.ison.app.message.request.LoginForm;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.message.response.JwtResponse;
import com.ison.app.services.RolesService;
import com.ison.app.services.UserService;
import com.ison.app.services.impl.UserDetailsServiceImpl;
import com.ison.app.services.impl.UserPrinciple;

import oracle.sql.TIMESTAMP;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthRestAPIs {
 
    @Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    RolesService rolesService;
    
    @Autowired
    UserService userService;
 
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;
    
    @Value("${app.jwtExpiration}")
    private int jwtExpiration;
 
    @PostMapping("/signin")
    public ResponseEntity<GenericResponse> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        
        UserPrinciple userPrinciple =   (UserPrinciple) authentication.getPrincipal();
        GenericResponse genericResponse = new GenericResponse();
		if ("BLOCKED".equalsIgnoreCase(userPrinciple.getStatus())) {
			genericResponse.setStatus(1002);
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure -> Your account is blocked. please contact the system administrator");
			return ResponseEntity.ok(new GenericResponse(genericResponse));
		} else if ("INACTIVE".equalsIgnoreCase(userPrinciple.getStatus())) {
			genericResponse.setStatus(1003);
			genericResponse.setError("Failure");
			genericResponse.setMessage("Failure -> Your account is inactive. please contact the system administrator");
			return ResponseEntity.ok(new GenericResponse(genericResponse));
		}
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        Date date = new Date((new Date()).getTime() + jwtExpiration);
        long dateTime = date.getTime();
        Timestamp expiryDate = new Timestamp(dateTime);
        genericResponse.setStatus(2002);
        genericResponse.setError("Success");
        genericResponse.setMessage("Logged in Successfully");
        genericResponse.setValue(new JwtResponse(jwt, userPrinciple.getAuthorities().toString(), String.valueOf(jwtExpiration), expiryDate));
        return ResponseEntity.ok(new GenericResponse(genericResponse));
    }

    @PostMapping("/auth/test")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> TestUser() throws Exception {
    	return ResponseEntity.ok().body("JWT Success");
    	
    }
}
