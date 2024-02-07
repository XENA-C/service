package org.example.storeadmin.domain.authorization.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.storeuser.enums.StoreUserRole;
import org.example.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession implements UserDetails{ //UserDetail 은 Interface -> 상속 후 재정의

    //user 정보
    private Long userId;

    private String email;

    private String password;

    private StoreUserStatus status;

    private LocalDateTime registeredAt;

    private StoreUserRole role;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;


    //store 정보
    private Long storeId;

    private String storeName;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired(){
        return this.status ==StoreUserStatus.REGISTERED; //만료 안 된 상태
    }

    @Override
    public boolean isAccountNonLocked(){
        return this.status == StoreUserStatus.REGISTERED; //잠김 아님
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return this.status == StoreUserStatus.REGISTERED; //
    }

    @Override
    public boolean isEnabled(){
        return true;  //boolean 값 리턴
    }

}
