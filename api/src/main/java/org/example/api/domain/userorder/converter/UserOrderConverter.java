package org.example.api.domain.userorder.converter;

import org.example.api.common.annotation.Converter;
import org.example.api.domain.user.contoller.model.User;
import org.example.api.domain.userorder.controller.model.UserOrderResponse;
import org.example.db.storeMenu.StoreMenuEntity;
import org.example.db.useroder.UserOrderEntity;

import java.math.BigDecimal;
import java.util.List;

@Converter
public class UserOrderConverter {

    public UserOrderEntity toEntity(  //주문 내역 리스트 총합
            User user,
            List<StoreMenuEntity> storeMenuEntityList
    ){
            var totalAmount = storeMenuEntityList.stream()
                    .map(it -> it.getAmount())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return UserOrderEntity.builder()
                    .userId(user.getId())
                    .amount(totalAmount)
                    .build()
                    ;
    }

    public UserOrderResponse toResponse(
        UserOrderEntity userOrderEntity
    ){
        return UserOrderResponse.builder()
                .id(userOrderEntity.getId())
                .status(userOrderEntity.getStatus())
                .amount(userOrderEntity.getAmount())
                .orderedAt(userOrderEntity.getOrderedAt())
                .acceptedAt(userOrderEntity.getAcceptedAt())
                .cookingStartedAt(userOrderEntity.getCookingStartedAt())
                .deliveryStartedAt(userOrderEntity.getDeliveryStartedAt())
                .receivedAt(userOrderEntity.getReceivedAt())
                .build()
                ;
    }

}
