package org.example.api.domain.storemenu.converter;

import org.example.api.common.annotation.Converter;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.example.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.example.db.StoreMenu.StoreMenuEntity;

import java.util.Optional;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it ->{
                    return StoreMenuEntity.builder()
                            .storeId(request.getStoreId())
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build()
                            ;
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,""));

    };

    public Optional<StoreMenuResponse> toResponse(
            StoreMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getStoreId())
                            .name(storeMenuEntity.getName())
                            .storeId(storeMenuEntity.getStoreId())
                            .amount(storeMenuEntity.getAmount())
                            .status(storeMenuEntity.getStatus())
                            .thumbnailUrl(storeMenuEntity.getThumbnailUrl())
                            .linkCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build()
                            ;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "")));

    }

}
