package com.personal.doctor.CapstoneDesign.domain.posts;

import com.personal.doctor.CapstoneDesign.domain.users.Users;
import com.personal.doctor.CapstoneDesign.domain.users.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void 게시물_작성하기() {
        Users users=Users.builder()
                .userID("user")
                .userPassword("password")
                .build();
        usersRepository.save(users);

        Posts posts=Posts.builder()
                .title("title")
                .category("category")
                .question("question")
                .build();
        users.addPosts(posts);
        posts.setUser(users);
        postsRepository.save(posts);

        assertEquals(posts.getUsers().getId(), users.getId());
    }

}