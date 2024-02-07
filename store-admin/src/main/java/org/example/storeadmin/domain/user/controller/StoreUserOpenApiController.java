package org.example.storeadmin.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.db.storeuser.StoreUserRepository;
import org.example.storeadmin.domain.user.business.StoreUserBusiness;
import org.example.storeadmin.domain.user.controller.model.StoreUSerRegisterRequest;
import org.example.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store-user")
public class StoreUserOpenApiController {

    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("")//가입
    public StoreUserResponse register(
            @Valid
            @RequestBody StoreUSerRegisterRequest request
            ){

        var response = storeUserBusiness.register(request);
        return response;

    }
}
