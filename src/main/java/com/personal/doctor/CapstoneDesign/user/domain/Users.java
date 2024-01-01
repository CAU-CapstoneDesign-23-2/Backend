package com.personal.doctor.CapstoneDesign.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
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
    @Column(name = "users_id")
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

    @Column
    private String location;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    private Details details;

    @JsonManagedReference
    @OneToMany(mappedBy = "users")
    private List<Chat> chats = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "users")
    private List<Posts> posts = new ArrayList<>();

    @Builder
    public Users(String userID, String userPassword, String userName, String location) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.location = location;
        this.role = Role.USER;
    }

    public void updateRole() {
        this.role = Role.DOCTOR;
    }

    public void updateUserData(String userName, String location) {
        this.userName = userName;
        this.location = location;
    }

    public void updateDetails(Details details) {
        this.details = details;
    }

    public void addPosts(Posts post) {
        this.posts.add(post);
    }

    public void addChats(Chat chat) {
        this.chats.add(chat);
    }

    public void beforeDelete() {
        for (Posts post : posts) {
            post.setUsers(null);
        }
    }
}
