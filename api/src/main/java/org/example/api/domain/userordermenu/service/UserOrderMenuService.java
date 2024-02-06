package org.example.api.domain.userordermenu.service;

import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.user.contoller.model.User;
import org.example.db.user.enums.UserStatus;
import org.example.db.userordermenu.OrderMenuEntity;
import org.example.db.userordermenu.OrderMenuRepository;
import org.example.db.userordermenu.enums.OrderMenuStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {

    private final OrderMenuRepository OrderMenuRepository;

    public List<OrderMenuEntity> getUserOrderMenu(Long userOrderId){
        return OrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, OrderMenuStatus.REGISTERED);
    }

    public OrderMenuEntity order(
            OrderMenuEntity userOrderMenuEntity
    ){
        return Optional.ofNullable(userOrderMenuEntity)
                .map(it-> {
                    it.setStatus(OrderMenuStatus.REGISTERED);
                    return OrderMenuRepository.save(it);
                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,""));
    }

}
