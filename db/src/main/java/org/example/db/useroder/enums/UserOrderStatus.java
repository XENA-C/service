package org.example.db.useroder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),

    ORDER("주문"),

    ACCEPT("확인"),

    COOKING("요리 중"),

    DELIVERY("배달 중"),

    RECEIVE("완료")

    ;

    private String description;


}
