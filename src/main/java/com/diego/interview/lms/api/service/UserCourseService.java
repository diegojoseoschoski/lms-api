package com.diego.interview.lms.api.service;

import com.diego.interview.lms.api.dto.CreateUserCourseDTO;
import com.diego.interview.lms.api.dto.UserCourseDTO;
import com.diego.interview.lms.api.exception.NotFoundException;
import com.diego.interview.lms.api.exception.UserCourseValidationException;
import com.diego.interview.lms.api.mapper.UserCourseMapper;
import com.diego.interview.lms.api.model.Course;
import com.diego.interview.lms.api.model.User;
import com.diego.interview.lms.api.model.UserCourse;
import com.diego.interview.lms.api.repository.CourseRepository;
import com.diego.interview.lms.api.repository.UserCourseRepository;
import com.diego.interview.lms.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCourseService {

    public static final int MAX_DUE_DATE = 6;

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private UserCourseRepository userCourseRepository;
    private UserCourseMapper userCourseMapper;

    public UserCourseService(UserRepository userRepository, CourseRepository courseRepository, UserCourseRepository userCourseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
        this.userCourseMapper = UserCourseMapper.INSTANCE;
    }

    public void createUserCourse(Long userId, CreateUserCourseDTO createUserCourseDTO) {
        validate(createUserCourseDTO);
        User user = userRepository.getReferenceById(userId);
        createUserCourseDTO.getCourses().forEach(userCourse -> saveUserCourse(user, userCourse));

    }

    public List<UserCourseDTO> getAllCoursesByUserId(Long id) {
        return userCourseRepository.
                getAllCoursesByUserId(id)
                .stream()
                .map(userCourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void saveUserCourse(User user, Long userCourse) {
        Course course = courseRepository.findById(userCourse).orElseThrow(() -> new NotFoundException("Course not found"));

        userCourseRepository.save(
                UserCourse
                        .builder()
                        .course(course)
                        .user(user)
                        .dueDate(LocalDate.now().plusMonths(MAX_DUE_DATE))
                        .build()
        );
    }

    private void validate(CreateUserCourseDTO createUserCourseDTO) {
        validateMaxCourses(createUserCourseDTO.getCourses());
    }

    private void validateMaxCourses(List<Long> courses) {
        if (courses != null && courses.size() > 3) {
            throw new UserCourseValidationException("Select a max of 3 course at the same time.");
        }
    }

}
