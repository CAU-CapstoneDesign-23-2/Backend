package com.personal.doctor.CapstoneDesign.chat.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000, nullable = false)    // 사용자 질문
    private String requestText;

    @Column(length = 2000, nullable = false)    // Bard 대답
    private String responseText;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Users_id")
    private Users users;

    @Builder
    public Chat(String requestText, String responseText, Users users) {
        this.requestText = requestText;
        this.responseText = responseText;
        this.users = users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
