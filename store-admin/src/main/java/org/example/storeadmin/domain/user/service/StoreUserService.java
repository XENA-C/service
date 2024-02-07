package org.example.storeadmin.domain.user.service;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.example.db.storeuser.StoreUserEntity;
import org.example.db.storeuser.StoreUserRepository;
import org.example.db.storeuser.enums.StoreUserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUserService {

    private final StoreUserRepository storeUserRepository;
    private final PasswordEncoder passwordEncoder;
    //@Bean 등록--> 인터페이스 형식으로 호출o

    public StoreUserEntity register(
        StoreUserEntity storeUserEntity //외부에서 넘겨준 Entity 값을 인코딩하여 setPassword
    ){
        storeUserEntity.setStatus(StoreUserStatus.REGISTERED);
        storeUserEntity.setPassword(passwordEncoder.encode(storeUserEntity.getPassword()));
        storeUserEntity.setRegisteredAt(LocalDateTime.now());
        return storeUserRepository.save(storeUserEntity);
    }

    public Optional<StoreUserEntity> getRegisterUser(String email){

        return storeUserRepository.findFirstByEmailAndStatusOrderByIdDesc(email,StoreUserStatus.REGISTERED);

    }

}
