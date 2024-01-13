package org.example.api.common.exception;

import org.example.api.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription(); //--> 커스텀한 Exceptioon에서 반드시 정의해야 함

}
