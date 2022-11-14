package com.example.gazpromcup.services;

import com.example.gazpromcup.GazpromCupApplicationTests;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VkServiceCacheTest extends GazpromCupApplicationTests {
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private  VKService vk;

    @Test
    public void makeRequest_testCache() throws ClientException, ApiException {
        var userInfo = vk.getUserInfo(userId, token);
        var fromCache =
                ofNullable(cacheManager
                        .getCache("user-info"))
                        .map(c -> c.get(userId, GetResponse.class));
        assertEquals(userInfo.getId(), fromCache.get().getId());
    }

    @Test
    public void makeRequestMemberOf_testCache() throws ClientException, ApiException {
        var userInfo = vk.getUserInfo(userId, token);
        var isMember = vk.isGroupMember(userInfo.getId(), groupId, token);
        var fromCache =
                ofNullable(cacheManager
                        .getCache("is-group-member"))
                        .map(c -> c.get(asList(userInfo.getId(), groupId), Boolean.class));
        assertEquals(isMember, fromCache.get());

    }

}
