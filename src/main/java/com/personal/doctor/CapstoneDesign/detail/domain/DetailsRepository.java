package com.personal.doctor.CapstoneDesign.detail.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {

    Optional<Details> findById(Long id);

    @Query("SELECT d FROM Details d WHERE d.users.id = :id")
    Details findUserDetails(@Param("id") Long userId);

}
