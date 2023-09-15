package com.diego.interview.lms.api.mapper;

import com.diego.interview.lms.api.dto.CreateUserDTO;
import com.diego.interview.lms.api.dto.UserCourseDTO;
import com.diego.interview.lms.api.dto.UserDTO;
import com.diego.interview.lms.api.model.User;
import com.diego.interview.lms.api.model.UserCourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCourseMapper {

    UserCourseMapper INSTANCE = Mappers.getMapper(UserCourseMapper.class);

    UserCourseDTO toDTO(UserCourse userCourse);

}
