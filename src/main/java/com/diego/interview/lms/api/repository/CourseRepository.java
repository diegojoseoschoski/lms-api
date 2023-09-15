package com.diego.interview.lms.api.repository;

import com.diego.interview.lms.api.model.Course;
import com.diego.interview.lms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select case when count(c)> 0 then true else false end from Course c where lower(c.name) like lower(:name)")
    boolean existsCourseByName(@Param("name") String name);

}
