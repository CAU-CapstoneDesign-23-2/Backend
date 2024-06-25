package com.personal.doctor.CapstoneDesign.chat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.users.id = :id ORDER BY c.id ASC")
    List<Chat> findChatByUsersId(@Param("id") Long userId);

}
