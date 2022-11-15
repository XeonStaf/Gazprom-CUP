package com.example.gazpromcup.ErrorHandlers;


import com.vk.api.sdk.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ClientExceptionHandler {

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public ResponseEntity<AppError> catchClientException(ClientException e) {
        log.error(e.getLocalizedMessage());
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity
                .status(503)
                .contentType(MediaType.APPLICATION_JSON)
                .body(AppError.builder()
                        .errorMessage(e.getLocalizedMessage())
                        .errorType("CLIENT-ERROR")
                        .build());
    }
}
