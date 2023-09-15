package com.diego.interview.lms.api.controller;

import com.diego.interview.lms.api.dto.*;
import com.diego.interview.lms.api.service.UserCourseService;
import com.diego.interview.lms.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "${client.url}")
public class UserCourseController {

    private UserCourseService userCourseService;

    public UserCourseController(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }


    @Operation(summary = "Add courses for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user courses")
    })
    @PostMapping(path = "/{id}/courses",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserCourse(@PathVariable("id") Long id, @Valid @RequestBody CreateUserCourseDTO createUserCourseDTO) {
        userCourseService.createUserCourse(id,createUserCourseDTO);
    }

    @Operation(summary = "Get all users courses")
    @GetMapping("/{id}/courses")
    public List<UserCourseDTO> getAllCoursesByUserId(@PathVariable("id") Long id) {
        return userCourseService.getAllCoursesByUserId(id);
    }
}
