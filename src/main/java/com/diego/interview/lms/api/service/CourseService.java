package com.diego.interview.lms.api.service;

import com.diego.interview.lms.api.dto.CourseDTO;
import com.diego.interview.lms.api.dto.CreateCourseDTO;
import com.diego.interview.lms.api.exception.CourseValidationException;
import com.diego.interview.lms.api.mapper.CourseMapper;
import com.diego.interview.lms.api.model.Course;
import com.diego.interview.lms.api.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
        this.courseMapper = CourseMapper.INSTANCE;
    }

    public void createCourse(CreateCourseDTO createCourseDTO) {
        validate(createCourseDTO);
        courseRepository.save(courseMapper.toEntity(createCourseDTO));
    }

    private void validate(CreateCourseDTO createCourseDTO) {
        validateCourseDuplicate(createCourseDTO.getName());
    }

    private void validateCourseDuplicate(String name) {
        if(courseRepository.existsCourseByName(name)){
            throw new CourseValidationException(String.format("This name %s is already in use", name));
        }

    }

    public List<CourseDTO> getAllCourses(){
        return courseRepository
                .findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
        return courseMapper.toDTO(course);
    }

    public CourseDTO updateCourse(Long id, CreateCourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

       return courseMapper.toDTO(courseRepository.save(course));

    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
