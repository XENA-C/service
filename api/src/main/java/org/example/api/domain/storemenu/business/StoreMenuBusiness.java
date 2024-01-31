package org.example.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.example.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.example.api.domain.storemenu.converter.StoreMenuConverter;
import org.example.api.domain.storemenu.service.StoreMenuService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public Optional<StoreMenuResponse> register(
        StoreMenuRegisterRequest request
    ){
        //req -> entity -> save -> response
        var entity = storeMenuConverter.toEntity(request);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);

        return response;

    }

    //특정 가게의 정보 검색

    public List<StoreMenuResponse> search(
        Long storeId
    ){
        var list = storeMenuService.getStoreMenuByStoreId(storeId);
        return list.stream()
                .map(it-> {
                    return storeMenuConverter.toResponse(it); //.map(storeMenuConverter::toResponse)
                })
                 .collect(Collectors.toList());

    }


}
