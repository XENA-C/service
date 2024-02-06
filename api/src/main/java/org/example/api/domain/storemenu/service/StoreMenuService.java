package org.example.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.db.storeMenu.StoreMenuEntity;
import org.example.db.storeMenu.StoreMenuRepository;
import org.example.db.storeMenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    //메뉴를 가져오는 메소드
    public StoreMenuEntity getStoreMenuWithThrow(Long id){
        var entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, ""));
    }

    //StoreId 를 통해 메뉴 검색
    public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId){
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
    }

    //메뉴등록
    public StoreMenuEntity register(
            StoreMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(storeMenuEntity)
                .map(it ->{
                    storeMenuEntity.setStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                        })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, ""));
    };

}
