package com.diego.interview.lms.api;

import com.diego.interview.lms.api.dto.CreateUserDTO;
import com.diego.interview.lms.api.dto.UserDTO;
import com.diego.interview.lms.api.exception.NotFoundException;
import com.diego.interview.lms.api.mapper.UserMapper;
import com.diego.interview.lms.api.model.Role;
import com.diego.interview.lms.api.model.User;
import com.diego.interview.lms.api.repository.RoleRepository;
import com.diego.interview.lms.api.repository.UserRepository;
import com.diego.interview.lms.api.service.UserService;
import com.diego.interview.lms.api.type.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    public static final String USR_NAME = "admin";
    public static final String USR_TST = "testuser";
    public static final String USR_PASS = "testpassword";
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;


    @Test
    void testSignInValidUser() {
        User mockUser = new User();
        mockUser.setFirstName(USR_NAME);
        mockUser.setLastName(USR_NAME);

        when(userRepository.findByUsernameAndPassword(USR_TST, USR_PASS)).thenReturn(Optional.of(mockUser));

        UserDTO mockUserDTO = new UserDTO();
        mockUserDTO.setFirstName(USR_NAME);
        mockUserDTO.setLastName(USR_NAME);


        UserDTO userDTO = userService.signIn(USR_TST, USR_PASS);

        assertNotNull(userDTO);
        assertEquals(USR_NAME, userDTO.getFirstName());
        assertEquals(USR_NAME, userDTO.getLastName());

        verify(userRepository, times(1)).findByUsernameAndPassword(USR_TST, USR_PASS);

    }

    @Test
    void testSignInInvalidUser() {
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.signIn("nonexistentuser", "invalidpassword"));

        verify(userRepository, times(1)).findByUsernameAndPassword("nonexistentuser", "invalidpassword");

        verify(userMapper, never()).toUserDTO(any(User.class));
    }

    @Test
    void testCreateUser() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setDob(LocalDate.of(2005, 1, 1)); // Age 18 (valid)
        createUserDTO.setEmail("test@example.com");

        Role mockRole = new Role();
        mockRole.setName(RoleType.ROLE_STUDENT);

        when(roleRepository.findByName(RoleType.ROLE_STUDENT)).thenReturn(Optional.of(mockRole));

        userService.createUser(createUserDTO);

        verify(userRepository, times(1)).save(any(User.class));

        verify(userMapper, never()).toEntity(any(CreateUserDTO.class));
    }



}