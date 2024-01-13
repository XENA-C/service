package org.example.api.common.error;

import javax.sql.rowset.serial.SerialStruct;

public interface ErrorCodeIfs {
    //인터페이스 메소드를 통해 각각 클래스가 정의한 에러코드 호출

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();

}

