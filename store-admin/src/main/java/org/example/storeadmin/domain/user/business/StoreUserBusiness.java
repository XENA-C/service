package org.example.storeadmin.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.db.store.StoreEntity;
import org.example.db.store.StoreRepository;
import org.example.db.store.enums.StoreStatus;
import org.example.db.storeuser.StoreUserEntity;
import org.example.db.storeuser.StoreUserRepository;
import org.example.storeadmin.domain.user.controller.model.StoreUSerRegisterRequest;
import org.example.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.example.storeadmin.domain.user.converter.StoreUserConverter;
import org.example.storeadmin.domain.user.service.StoreUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;

    private final StoreUserService storeUserService;

    private final StoreRepository storeRepository; //TODO SERVICE 로 변경하기

    public StoreUserResponse register(
            StoreUSerRegisterRequest request
    ){
        var storeEntity = storeRepository.findFirstByNameAndStatusOrderByIdDesc(request.getStoreName(), StoreStatus.REGISTERED);
        var entity = storeUserConverter.toEntity(request, storeEntity.get());
        var newEntity = storeUserService.register(entity); //저장 처리

        var response = storeUserConverter.toResponse(newEntity, storeEntity.get());
        return response;

    }




}
