package org.example.api.domain.storemenu.controller;


import lombok.RequiredArgsConstructor;
import org.example.api.common.api.Api;
import org.example.api.domain.storemenu.business.StoreMenuBusiness;
import org.example.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {

    //로그인 -> 가게를 클릭 선택(storeId)-> 메뉴정보 응답

    private final StoreMenuBusiness storeMenuBusiness;
    public Api<List<StoreMenuResponse>> search(
            @RequestParam Long storeId
    ){
        var response = storeMenuBusiness.search(storeId);
        return Api.OK(response);
    }

}
