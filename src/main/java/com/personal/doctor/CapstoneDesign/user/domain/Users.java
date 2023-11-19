package com.personal.doctor.CapstoneDesign.user.domain;

import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.detail.domain.Details;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String userName;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Posts> posts = new ArrayList<>();

    @OneToOne(mappedBy = "users", fetch = FetchType.LAZY)
    private Details details;

    @Builder
    public Users(String userID, String userPassword, String userName) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.role = Role.USER;
    }

    public Long updateRole() {
        this.role = Role.DOCTOR;
        return this.id;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updateDetails(Details details) {
        this.details = details;
    }

    public void addPosts(Posts post) {
        this.posts.add(post);
    }

    public void beforeDelete() {
        for (Posts post : posts) {
            post.setUser(null);
        }
    }
}
