package com.personal.doctor.CapstoneDesign.detail.domain;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Details {

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
    private String hobby1;

    @Column
    private String hobby2;

    @Column
    private String hobby3;

    // 복용중인 약
    @Column
    private String medicine;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Users users;

    @Builder
    public Details(String age, String gender, String disease1, String disease2, String disease3,
                   String surgery, String hobby1, String hobby2, String hobby3, String medicine, Users users) {
        this.age = age;
        this.gender = gender;
        this.disease1 = disease1;
        this.disease2 = disease2;
        this.disease3 = disease3;
        this.surgery = surgery;
        this.hobby1 = hobby1;
        this.hobby2 = hobby2;
        this.hobby3 = hobby3;
        this.medicine = medicine;
        this.users = users;
    }

}
