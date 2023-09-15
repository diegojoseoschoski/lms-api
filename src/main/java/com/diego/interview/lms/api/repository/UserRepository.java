package com.diego.interview.lms.api.repository;

import com.diego.interview.lms.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select case when count(u)> 0 then true else false end from User u where lower(u.email) like lower(:email)")
    boolean existsUserByEmail(@Param("email") String email);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
