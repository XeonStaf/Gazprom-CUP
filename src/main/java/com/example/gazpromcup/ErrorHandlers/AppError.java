package com.example.gazpromcup.ErrorHandlers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppError {
    private String errorType;
    private String errorMessage;
}
