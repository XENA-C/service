package org.example.api.domain.user.contoller.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class UserResponse {
    //Controller 에서 Login 요청을 받은 후 내려주는 응답객체

    private Long id; //DB 저장된 index id를 내려줌

    private String name;

    private String email;

 // private String password;

    private UserStatus status;

    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;

}
