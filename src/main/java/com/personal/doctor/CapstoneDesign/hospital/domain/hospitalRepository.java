package com.personal.doctor.CapstoneDesign.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hospitalRepository extends JpaRepository<hospital, Long> {
}
