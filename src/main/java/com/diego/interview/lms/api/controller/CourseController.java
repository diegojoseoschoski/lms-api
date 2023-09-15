package com.diego.interview.lms.api.controller;

import com.diego.interview.lms.api.dto.CourseDTO;
import com.diego.interview.lms.api.dto.CreateCourseDTO;
import com.diego.interview.lms.api.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin(origins = "${client.url}")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Create a new course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created course")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        courseService.createCourse(createCourseDTO);
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @Operation(summary = "Get a course by id")
    @GetMapping(path = "/{id}")
    public CourseDTO getCourseById(@PathVariable("id") Long id){
        return courseService.getCourseById(id);
    }

    @Operation(summary = "Update a course")
    @PutMapping(path = "/{id}")
    public CourseDTO updateCourse(@PathVariable("id") Long id,@Valid @RequestBody CreateCourseDTO courseDTO){
        return courseService.updateCourse(id, courseDTO);
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping(path = "/{id}")
    public void deleteCourse(@PathVariable("id") Long id){
        courseService.deleteCourse(id);
    }

}
