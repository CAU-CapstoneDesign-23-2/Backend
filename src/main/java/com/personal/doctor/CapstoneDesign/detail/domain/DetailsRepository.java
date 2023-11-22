package com.personal.doctor.CapstoneDesign.detail.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {

    Optional<Details> findById(Long id);

}
