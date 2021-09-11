package com.faez.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ApiException {
    private final String message;
//    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ApiException(String message,
//                        Throwable throwable,
                        HttpStatus httpStatus,
                        ZonedDateTime timeStamp) {
        this.message = message;
//        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

}
