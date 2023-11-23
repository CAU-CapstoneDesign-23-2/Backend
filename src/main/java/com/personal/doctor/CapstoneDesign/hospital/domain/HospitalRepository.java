package com.personal.doctor.CapstoneDesign.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 내과, 성형외과 등을 찾을 때 병원 이름에 포함되어 있는 경우
    @Query("SELECT h FROM Hospital h WHERE h.name LIKE %:type% ORDER BY h.id DESC")
    List<Hospital> findHospitalsByName(@Param("keyword") String type);

}
