package com.personal.doctor.CapstoneDesign.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    // 내과, 성형외과 등을 찾을 때 병원 이름에 포함되어 있는 경우
    @Query("SELECT h FROM Hospital h WHERE (h.name LIKE %:type%) AND ((h.city LIKE %:location%) OR (h.district LIKE %:location%)) ORDER BY h.id DESC")
    List<Hospital> findHospitalsByName(@Param("location") String location,
                                       @Param("type") String type);

    // 사용자가 입력한 위치를 바탕으로 병원 정보 찾기
    @Query("SELECT h FROM Hospital h WHERE (h.city = :location) AND (h.district = :location OR :location IS NULL) AND (h.town = :location OR :location IS NULL) ORDER BY h.id DESC")
    List<Hospital> findHospitalByAddress(@Param("location") String location);

    // 사용자가 입력한 위치를 바탕으로 병원 정보 찾기 메인에 노출할 1개 반환
    @Query("SELECT h FROM Hospital h WHERE h.district = :location ORDER BY h.id ASC")
    List<Hospital> findHospitalsByAddress(@Param("location") String location);

}
