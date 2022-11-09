package com.example.gazpromcup.controllers;
import com.example.gazpromcup.ErrorHandlers.AppError;
import com.example.gazpromcup.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VkController {
    @Operation(summary = "Check if user is member of the group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Received result from VK",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseIsUserMemberOfGroup.class)) }),
            @ApiResponse(responseCode = "502", description = "VK-API-ERROR",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AppError.class)) }),
            @ApiResponse(responseCode = "503", description = "CLIENT-ERROR",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppError.class)) }) })
    @PostMapping("/api/is-member-of-group")
    @ResponseBody
    public ResponseEntity<?> isUserMemberOfGroup (@RequestBody BodyIsUserMemberOfGroup data,
                                                  @RequestHeader("vk_service_token") String token)
            throws ClientException, ApiException {
        var user = VKService.getUserInfo(data.getUser_id(), token);
        var member = VKService.isGroupMember(user.getId(), data.getGroup_id(), token);

        var result = ResponseIsUserMemberOfGroup.builder()
                .last_name(user.getLastName())
                .first_name(user.getFirstName())
                .middle_name(user.getNickname())
                .member(member)
                .build();

        return ResponseEntity.ok().body(result);
    }
}


