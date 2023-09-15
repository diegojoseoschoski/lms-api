package com.diego.interview.lms.api.controller;

import com.diego.interview.lms.api.dto.CreateUserCourseDTO;
import com.diego.interview.lms.api.dto.CreateUserDTO;
import com.diego.interview.lms.api.dto.SigninDTO;
import com.diego.interview.lms.api.dto.UserDTO;
import com.diego.interview.lms.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "${client.url}")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Sign in a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user"),
    })
    @PostMapping(path = "/signin")
    public UserDTO signIn(@Valid @RequestBody SigninDTO signinDTO) {
        return userService.signIn(signinDTO.getUsername(), signinDTO.getPassword());
    }

    @Operation(summary = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
    }

}
