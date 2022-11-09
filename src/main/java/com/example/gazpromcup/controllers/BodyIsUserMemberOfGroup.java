package com.example.gazpromcup.controllers;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
public class BodyIsUserMemberOfGroup {
        @NotBlank

        private String user_id;
        @NotBlank
        private String group_id;

}
