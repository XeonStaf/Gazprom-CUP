package com.example.gazpromcup.ErrorHandlers;


import com.vk.api.sdk.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<AppError> catchVkApiException(ApiException e) {
        log.error(e.getLocalizedMessage());
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity
                .status(502)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppError.builder()
                        .errorMessage(e.getLocalizedMessage())
                        .errorType("VK-API-ERROR")
                        .build());

    }
}
