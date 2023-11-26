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

    @Column(nullable = false)   // 사용자의 질문이면 0,
    private Long type;          // 생성형 AI의 답변이면 1

    @Column(length = 500, nullable = false)
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Users_id")
    private Users users;

    @Builder
    public Chat(Long type, String content, Users users) {
        this.type = type;
        this.content = content;
        this.users = users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
