package com.personal.doctor.CapstoneDesign.userDetail.domain;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String gender;

    // 앓은 적이 있거나 앓고 있는 질병 최대 3개 선택하여 저장
    @Column
    private String disease1;

    @Column
    private String disease2;

    @Column
    private String disease3;

    // 수술 이력
    @Column
    private String surgery;

    // 최근에 하고 있는 격렬한 활동 최대 3개 선택하여 저장
    @Column
    private String activity1;

    @Column
    private String activity2;

    @Column
    private String activity3;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Users users;

    @Builder
    public UserDetails(String age, String gender, String disease1, String disease2, String disease3,
                       String surgery, String activity1, String activity2, String activity3, Users users) {
        this.age = age;
        this.gender = gender;
        this.disease1 = disease1;
        this.disease2 = disease2;
        this.disease3 = disease3;
        this.surgery = surgery;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.users = users;
    }

}
