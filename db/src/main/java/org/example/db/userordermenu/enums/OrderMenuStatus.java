package org.example.db.userordermenu.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private String description;

}
