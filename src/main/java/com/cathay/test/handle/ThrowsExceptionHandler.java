package com.cathay.test.handle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cathay.test.exception.CommonException;
import com.cathay.test.vo.ThrowableResponseVo;

@RestControllerAdvice
public class ThrowsExceptionHandler {

    @ExceptionHandler(CommonException.class)
    ResponseEntity<ThrowableResponseVo> handleCustomizedException(CommonException ex) {
        ThrowableResponseVo throwableResponseVo = new ThrowableResponseVo(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(throwableResponseVo);
    }
}
