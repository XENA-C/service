package org.example.storeadmin.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.example.db.store.StoreEntity;
import org.example.db.store.StoreRepository;
import org.example.db.store.enums.StoreStatus;
import org.example.db.storeuser.StoreUserEntity;
import org.example.storeadmin.domain.authorization.model.UserSession;
import org.example.storeadmin.domain.user.controller.model.StoreUSerRegisterRequest;
import org.example.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service  //Component 어노테이션 수행
public class StoreUserConverter {

//    // TODO store service 생기면 이관 필요
//    private final StoreRepository storeRepository;  컨버터 클래스는 변환만 수행

    public StoreUserEntity toEntity(
            StoreUSerRegisterRequest request,
            StoreEntity storeEntity
        ){

        var storeName = request.getStoreName();
      //var storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(storeName, StoreStatus.REGISTERED);

        return StoreUserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .storeId(storeEntity.getId()) //TODO Null 일 때 에러체크 필요
                .build()
                ;
    }



    public StoreUserResponse toResponse(
            StoreUserEntity storeUserEntity,
            StoreEntity storeEntity
    ){
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(storeUserEntity.getId())
                                .email(storeUserEntity.getEmail())
                                .status(storeUserEntity.getStatus())
                                .role(storeUserEntity.getRole())
                                .registeredAt(storeUserEntity.getRegisteredAt())
                                .unregisteredAt(storeUserEntity.getUnregisteredAt())
                                .lastLoginAt(storeUserEntity.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(storeEntity.getId())
                                .name(storeEntity.getName())
                                .build()
                )
                .build();

    }


    public StoreUserResponse toResponse(UserSession userSession){
       //로그인 사용자 세션이 들어오면 아래 정보 반환
        return StoreUserResponse.builder()
                .user(
                        StoreUserResponse.UserResponse.builder()
                                .id(userSession.getUserId())
                                .email(userSession.getEmail())
                                .status(userSession.getStatus())
                                .role(userSession.getRole())
                                .registeredAt(userSession.getRegisteredAt())
                                .unregisteredAt(userSession.getUnregisteredAt())
                                .lastLoginAt(userSession.getLastLoginAt())
                                .build()
                )
                .store(
                        StoreUserResponse.StoreResponse.builder()
                                .id(userSession.getStoreId())
                                .name(userSession.getStoreName())
                                .build()
                )
                .build();

    }


}
