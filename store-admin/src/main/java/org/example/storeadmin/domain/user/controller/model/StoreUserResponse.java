package org.example.storeadmin.domain.user.controller.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.storeuser.enums.StoreUserRole;
import org.example.db.storeuser.enums.StoreUserStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse{

    private UserResponse user;
    private StoreResponse store;


//inner class : 외부에서 접근이 불가능하도록?? (public X)
//storeUserResponse. 으로 접근
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse{  //사용자 정보

        private Long id;

        private String email;

        private StoreUserStatus status;

        private StoreUserRole role;

        private LocalDateTime registeredAt;

        private LocalDateTime unregisteredAt;

        private LocalDateTime lastLoginAt;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreResponse{  //가게 정보

        private Long id; //가맹점 id
        private String name;

    }

}
