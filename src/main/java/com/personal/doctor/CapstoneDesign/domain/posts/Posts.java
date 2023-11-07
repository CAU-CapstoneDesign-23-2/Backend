package com.personal.doctor.CapstoneDesign.domain.posts;

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

    @Builder
    public Posts(String category, String title, String question) {
        this.category = category;
        this.title = title;
        this.question = question;
    }

    public void updatePosts(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public void answered(String docName, String answer) {
        this.docName = docName;
        this.answer = answer;
    }

}
