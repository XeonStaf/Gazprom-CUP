package com.example.gazpromcup.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseIsUserMemberOfGroup {
    private String last_name;
    private String first_name;
    private String middle_name;
    private Boolean member;
}
