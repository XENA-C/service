package org.example.storeadmin.domain.authorization;

import lombok.RequiredArgsConstructor;
import org.example.db.store.StoreRepository;
import org.example.db.store.enums.StoreStatus;
import org.example.db.storeuser.StoreUserRepository;
import org.example.storeadmin.domain.authorization.model.UserSession;
import org.example.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.example.storeadmin.domain.user.service.StoreUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    @Override  //로그인 창의 userName --> 정상 유저 인증 시 반환
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var storeUserEntity = storeUserService.getRegisterUser(username);
        var storeEntity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(
                storeUserEntity.get().getStoreId(),
                StoreStatus.REGISTERED); //등록된 가맹점 가져오기

        return storeUserEntity.map(it -> {  //User 대신 UserSession return

             var userSession = UserSession.builder()
                     .userId(it.getId())
                     .email(it.getEmail())
                     .status(it.getStatus())
                     .role(it.getRole())
                     .registeredAt(it.getRegisteredAt())
                     .lastLoginAt(it.getLastLoginAt())
                     .unregisteredAt(it.getUnregisteredAt())

                     .storeId(storeEntity.get().getId())
                     .storeName(storeEntity.get().getName())
                     .build()
                     ;

             return userSession;

//             var user = User.builder()
//                            .username(it.getEmail())
//                            .roles(it.getRole().toString())
//                            .password(it.getPassword())
//                            .build();
//
//             return user;

                })
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}