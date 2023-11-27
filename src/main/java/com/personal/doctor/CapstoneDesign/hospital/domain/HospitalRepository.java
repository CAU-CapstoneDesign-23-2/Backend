package com.personal.doctor.CapstoneDesign.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 내과, 성형외과 등을 찾을 때 병원 이름에 포함되어 있는 경우
    @Query("SELECT h FROM Hospital h WHERE (h.name LIKE %:type%) AND (h.city LIKE %:city%) ORDER BY h.id DESC")
    List<Hospital> findHospitalsByName(@Param("city") String city,
                                       @Param("type") String type);

    // 사용자가 입력한 위치를 바탕으로 병원 정보 찾기
    @Query("SELECT h FROM Hospital h WHERE (h.city = :city) AND (h.district = :district OR :district IS NULL) AND (h.town = :town OR :town IS NULL) ORDER BY h.id DESC")
    List<Hospital> findHospitalByAddress(@Param("city") String city,
                                         @Param("district") String district,
                                         @Param("town") String town);

}
