package com.diego.interview.lms.api.mapper;

import com.diego.interview.lms.api.dto.CourseDTO;
import com.diego.interview.lms.api.dto.CreateCourseDTO;
import com.diego.interview.lms.api.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(CreateCourseDTO courseDTO);
    CourseDTO toDTO(Course course);
}
