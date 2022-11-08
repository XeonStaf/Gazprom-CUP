package com.example.gazpromcup.controllers;
import com.example.gazpromcup.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class VkController {

    @PostMapping("/api/is-member-of-group")
    @ResponseBody
    public ResponseEntity<?> isUserMemberOfGroup (@RequestBody BodyIsUserMemberOfGroup data,
                                                  @RequestHeader("vk_service_token") String token)
            throws ClientException, ApiException {
        var user = VKService.getUserInfo(data.getUser_id(), token);
        var member = VKService.isGroupMember(user.getId(), data.getGroup_id(), token);
        var result = new HashMap<>() {{
            put("last_name", user.getLastName());
            put("first_name", user.getFirstName());
            put("middle_name", user.getNickname());
            put("member", member);
        }};

        return ResponseEntity.ok().body(result);
    }
}


