package com.personal.doctor.CapstoneDesign.community.domain;

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
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String question;

    @Column
    private String docName;

    @Column
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Users_id")
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

    public void setUser(Users users) {
        this.users = users;
    }

}
