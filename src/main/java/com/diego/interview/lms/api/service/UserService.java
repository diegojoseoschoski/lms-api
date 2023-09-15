package com.diego.interview.lms.api.service;

import com.diego.interview.lms.api.dto.CreateUserCourseDTO;
import com.diego.interview.lms.api.dto.CreateUserDTO;
import com.diego.interview.lms.api.dto.UserDTO;
import com.diego.interview.lms.api.exception.NotFoundException;
import com.diego.interview.lms.api.exception.UserValidationException;
import com.diego.interview.lms.api.mapper.UserMapper;
import com.diego.interview.lms.api.model.Course;
import com.diego.interview.lms.api.model.Role;
import com.diego.interview.lms.api.model.User;
import com.diego.interview.lms.api.model.UserCourse;
import com.diego.interview.lms.api.repository.CourseRepository;
import com.diego.interview.lms.api.repository.RoleRepository;
import com.diego.interview.lms.api.repository.UserCourseRepository;
import com.diego.interview.lms.api.repository.UserRepository;
import com.diego.interview.lms.api.type.RoleType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    public static final int MIN_AGE = 16;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.userMapper = UserMapper.INSTANCE;
        this.roleRepository = roleRepository;
    }

    public UserDTO signIn(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new NotFoundException("User not found"));

        UserDTO userDTO = userMapper.toUserDTO(user);
        userDTO.setToken(UUID.randomUUID().toString());

        return  userDTO;
    }

    public void createUser(CreateUserDTO createUserDTO) {
        validate(createUserDTO);
        userRepository.save(create(createUserDTO));

    }

    private User create(CreateUserDTO createUserDTO) {

        User user = userMapper.toEntity(createUserDTO);

        Role role = roleRepository.findByName(RoleType.ROLE_STUDENT)
                                  .orElseThrow(() -> new NotFoundException(String.format("Role %s not found", RoleType.ROLE_STUDENT)));


        user.setRoles(Set.of(role));

        return user;
    }

    private void validate(CreateUserDTO createUserDTO) {
        validateUserAge(createUserDTO.getDob());
        validateUserDuplicate(createUserDTO.getEmail());

    }

    private void validateUserDuplicate(String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new UserValidationException(String.format("This email %s is already in use", email));
        }
    }

    private void validateUserAge(LocalDate dob) {
        if (isUserUnder16(dob)) {
            throw new UserValidationException("User age is under 16");
        }
    }

    private boolean isUserUnder16(LocalDate dob) {
        int age =  Period.between(dob, LocalDate.now()).getYears();
        return MIN_AGE >  age;
    }
}
