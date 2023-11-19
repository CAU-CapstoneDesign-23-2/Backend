package com.personal.doctor.CapstoneDesign.user.domain;

import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @AfterEach
    public void deleteAll() {
        usersRepository.deleteAll();
    }

    @Test
    @Rollback
    public void 사용자_저장() {
        Users user1 = Users.builder()
                .userID("ID1")
                .userPassword("PW1")
                .userName("Name1")
                .build();
        usersRepository.save(user1);

        Users user2 = Users.builder()
                .userID("ID2")
                .userPassword("PW2")
                .build();
        usersRepository.save(user2);

        assertEquals(2, usersRepository.count());
    }

    @Test
    @Rollback
    public void 사용자_findById() {
        Users user = Users.builder()
                .userID("ID")
                .userPassword("PW")
                .userName("Name")
                .build();
        usersRepository.save(user);

        Users found = usersRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        assertEquals("Name", found.getUserName());
    }

    @Test
    @Rollback
    public void 사용자_findByUserId() {
        Users user = Users.builder()
                .userID("ID")
                .userPassword("PW")
                .userName("Name")
                .build();
        usersRepository.save(user);

        Users found = usersRepository.findByUserID("ID")
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        assertEquals("Name", found.getUserName());
    }
}