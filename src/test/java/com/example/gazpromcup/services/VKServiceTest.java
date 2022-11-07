package com.example.gazpromcup.services;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class VKServiceTest {

    @Value("${vk-token}")
    private String token;
    private final String userId = "152855497";
    private final String groupId = "itmostudents";

    @BeforeEach
    void setUp() {

    }

    @Test
    void getUserInfo_checkResult() throws ClientException, ApiException {
        var result = VKService.getUserInfo(userId, token);
        assertEquals("Леша", result.getFirstName());
        assertEquals("Астафьев", result.getLastName());
    }


    @Test
    void isGroupMember_checkResult() throws ClientException, ApiException {
        var result = VKService.isGroupMember(Integer.valueOf(userId), groupId, token);
        assertTrue(result);
    }
}