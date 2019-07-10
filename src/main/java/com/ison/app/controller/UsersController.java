package com.ison.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ison.app.configuration.SpringSecurityAuditorAware;
import com.ison.app.message.request.SearchForm;
import com.ison.app.message.request.SignUpForm;
import com.ison.app.message.request.UserUpdateForm;
import com.ison.app.message.response.GenericResponse;
import com.ison.app.message.response.UsersResponse;
import com.ison.app.services.RolesService;
import com.ison.app.services.UserService;
import com.ison.app.services.impl.UserDetailsServiceImpl;
import com.ison.app.shared.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	RolesService rolesService;
	    
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    SpringSecurityAuditorAware springSecurityAuditorAware;
	   
	
	@PostMapping("/create")
    public ResponseEntity<GenericResponse> registerUser(@Valid @RequestBody SignUpForm signUpRequest) throws Exception {
    	GenericResponse genericResponse = null;
        if(userDetailsService.existsByUsername(signUpRequest.getEmployeeId())) {
        	genericResponse = new GenericResponse();
        	genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        	genericResponse.setError("Failure");
        	genericResponse.setMessage("Failure -> Employee Id is already taken!");
            return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.OK);
        }
 
        if(userDetailsService.existsByEmail(signUpRequest.getEmail())) {
        	genericResponse = new GenericResponse();
        	genericResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        	genericResponse.setError("Failure");
        	genericResponse.setMessage("Fail -> Email is already in use!");
            return new ResponseEntity<GenericResponse>(new GenericResponse(genericResponse), HttpStatus.OK);
        }
 
        // Creating user's account
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(signUpRequest, userDto);
        userDto.setPassword(encoder.encode(signUpRequest.getPassword()));
       /* Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();
        strRoles.forEach(role -> {
        	Roles adminRole = rolesService.findByName(role)
        			.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            roles.add(adminRole);
        });
        
        userDto.setRoles(roles);
   */
        userDto.setAutogenRolesId(signUpRequest.getRoleId());
        
        try {
        	userDto = userService.save(userDto);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
 
        genericResponse = new GenericResponse();
    	genericResponse.setStatus(HttpStatus.OK.value());
    	genericResponse.setError("Success");
    	genericResponse.setMessage("Success -> User registered successfully!");
    /*	UserDto usersDto = new UserDto();
    	BeanUtils.copyProperties(users, usersDto);*/
    	genericResponse.setValue(new UserDto(userDto));
        return ResponseEntity.ok().body(new GenericResponse(genericResponse));
    }
	    
    @PostMapping("/usersList")
    public ResponseEntity<GenericResponse> authenticateUser() throws Exception {
    	
    	System.out.println("UserName::"+springSecurityAuditorAware.getUserName());
    	List<UsersResponse> userList = userService.getUsersList();
    	GenericResponse genericResponse = null;
    	if(!userList.isEmpty()) { 
       	 genericResponse = new GenericResponse();
       	 genericResponse.setStatus(1005);
       	 genericResponse.setError("Success");
       	 genericResponse.setMessage("Success -> Users found!");
       	genericResponse.setValue(userList);
    	} else {
         genericResponse = new GenericResponse();
         genericResponse.setStatus(1001);
         genericResponse.setError("Failure");
         genericResponse.setMessage("Success -> Users not found!");
    	}
        return ResponseEntity.ok(new GenericResponse(genericResponse));
    }    
    
    @PostMapping("/update")
    public ResponseEntity<GenericResponse> UpdateUser(@Valid @RequestBody UserUpdateForm updateUserRequest) throws Exception {
    	UserDto userDto =  new UserDto();
    	BeanUtils.copyProperties(updateUserRequest, userDto);
    	userDto.setAutogenUsersId(updateUserRequest.getId());
    	userDto.setAutogenRolesId(updateUserRequest.getRoleId());
    	userDto.setClients(updateUserRequest.getClients());
    	boolean updateStatus = userService.UpDateUser(userDto);
    	GenericResponse genericResponse = null;
    	if(updateStatus) {
    	 genericResponse = new GenericResponse();
     	genericResponse.setStatus(1005);
     	genericResponse.setError("Success");
     	genericResponse.setMessage("Success -> User Updated successfully!");
    	}
        return ResponseEntity.ok(new GenericResponse(genericResponse));
    }  
	   
    @PostMapping("/search")
    public ResponseEntity<GenericResponse> search(@RequestBody SearchForm searchRequest) throws Exception {
    	List<UsersResponse> userList = userService.getUsersList();
    	GenericResponse genericResponse = null;
    	if(!userList.isEmpty()) {
       	 genericResponse = new GenericResponse();
       	 genericResponse.setStatus(1005);
       	 genericResponse.setError("Success");
       	 genericResponse.setMessage("Success -> Users found!");
       	genericResponse.setValue(userList);
    	} else {
         genericResponse = new GenericResponse();
         genericResponse.setStatus(1001);
         genericResponse.setError("Failure");
         genericResponse.setMessage("Success -> Users not found!");
    	}
        return ResponseEntity.ok(new GenericResponse(genericResponse));
    }
}
