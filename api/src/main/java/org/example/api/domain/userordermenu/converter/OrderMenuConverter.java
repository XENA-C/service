package org.example.api.domain.userordermenu.converter;

import org.example.api.common.annotation.Converter;
import org.example.db.storeMenu.StoreMenuEntity;
import org.example.db.useroder.UserOrderEntity;
import org.example.db.userordermenu.OrderMenuEntity;

@Converter
public class OrderMenuConverter {

    public static OrderMenuEntity toEntity(
            UserOrderEntity userOrderEntity,
            StoreMenuEntity storeMenuEntity
    ){
        return OrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build()
                ;

    };



}
