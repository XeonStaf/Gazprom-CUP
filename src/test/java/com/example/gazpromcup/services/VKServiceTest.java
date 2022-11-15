package com.example.gazpromcup.services;

import com.example.gazpromcup.GazpromCupApplicationTests;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VKServiceTest extends GazpromCupApplicationTests {

    @Autowired
    private VKService vk;


    @Test
    void getUserInfo_checkResult() throws ClientException, ApiException {
        var result = vk.getUserInfo(userId, token);
        assertEquals("Леша", result.getFirstName());
        assertEquals("Астафьев", result.getLastName());
    }


    @Test
    void isGroupMember_checkResult() throws ClientException, ApiException {
        var result = vk.isGroupMember(Integer.valueOf(userId), groupId, token);
        assertTrue(result);
    }
}