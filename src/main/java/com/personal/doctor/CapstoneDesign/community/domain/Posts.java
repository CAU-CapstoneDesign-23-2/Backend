package com.personal.doctor.CapstoneDesign.community.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String question;

    @Column
    private String docName;

    @Column
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Builder
    public Posts(String category, String title, String question, Users users) {
        this.category = category;
        this.title = title;
        this.question = question;
        this.users = users;
    }

    public void updatePosts(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public void answered(String docName, String answer) {
        this.docName = docName;
        this.answer = answer;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
