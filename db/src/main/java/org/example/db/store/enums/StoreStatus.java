package org.example.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {

    REGISTERED("등록"),

    UNREGISTERED("해지"); // 관리자 승인 후 해지

    private String description;

}
