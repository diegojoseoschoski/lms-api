package com.diego.interview.lms.api.repository;

import com.diego.interview.lms.api.model.User;
import com.diego.interview.lms.api.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    @Query("select uc from UserCourse uc where uc.user.id = :id")
    List<UserCourse> getAllCoursesByUserId(@Param("id") Long id);


}
