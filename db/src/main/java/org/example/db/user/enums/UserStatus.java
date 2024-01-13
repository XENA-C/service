package org.example.db.user.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {  //Enum 클래스 장점 : 코드 오타 방지, 지정된 데이터만 입력

    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private final String description;

}
