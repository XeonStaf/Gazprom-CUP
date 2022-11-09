package com.example.gazpromcup.services;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import lombok.Getter;
import org.springframework.stereotype.Service;


@Service
public class VKService {

    @Getter
    static
    class VkAndActor {
        private final VkApiClient vk;
        private final ServiceActor actor;

        public VkAndActor(VkApiClient vk, ServiceActor actor) {
            this.vk = vk;
            this.actor = actor;
        }
    }

    private static VkAndActor getVkAndActor(String token) {

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        ServiceActor actor = new ServiceActor(0, token);
        return new VkAndActor(vk, actor);
    }

    /**
     * Возращает ифнормацию о пользотваеле
     *
     * @param userId - userId или short_name
     * @param token  - токен
     * @return Информация о пользователе: имя, фамилия, отчество, user_id
     */
    public static GetResponse getUserInfo(String userId, String token) throws ClientException, ApiException {
        var vaa = getVkAndActor(token);
        var vk = vaa.vk;
        var actor = vaa.actor;
        var result = vk.users().get(actor)
                .fields(Fields.FIRST_NAME_NOM, Fields.FIRST_NAME_NOM, Fields.NICKNAME)
                .userIds(userId)
                .lang(Lang.RU)
                .execute();

        if (result.size() == 0)
            throw new ApiException("User with this username/user_id doesn't exists.");

        return result
                .get(0);
    }


    /**
     * D
     * @param userId - обязательно Integer
     * @param groupId - id или название
     * @param token - токен
     * @return True - если пользотваель состоит в группе <br>
     * False - иначе
     */
    public static boolean isGroupMember(Integer userId, String groupId, String token) throws ClientException, ApiException {
        var vaa = getVkAndActor(token);
        var vk = vaa.vk;
        var actor = vaa.actor;

        return vk.groups()
                .isMemberWithUserIds(actor, groupId, userId)
                .execute()
                .get(0)
                .isMember();
    }

}
