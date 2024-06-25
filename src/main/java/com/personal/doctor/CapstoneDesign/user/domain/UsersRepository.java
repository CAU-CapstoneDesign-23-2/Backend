package com.personal.doctor.CapstoneDesign.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findById(Long id);
    Optional<Users> findByUserID(String userID);

    @Query("SELECT u FROM Users u ORDER BY u.id DESC")
    List<Users> findAllDesc();
}
