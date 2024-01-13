package org.example.db.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.db.BaseEntity;
import org.example.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Data
@Entity //Entity(value=)와의 차이: 해당클래스의 이름을 스네이크 케이스로 변환하여 db 테이블과 매칭
@SuperBuilder //baseEntity를 상속
@EqualsAndHashCode(callSuper = true) //상속:
@Table(name = "user") //클래스의 이름과 같지않아도
@NoArgsConstructor @AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}
