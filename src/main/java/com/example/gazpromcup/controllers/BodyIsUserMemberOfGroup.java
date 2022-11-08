package com.example.gazpromcup.controllers;

import lombok.Data;
import lombok.Getter;

@Data
public class BodyIsUserMemberOfGroup {
        private String user_id;
        private String group_id;

        public String getUser_id() {
                return user_id;
        }

        public String getGroup_id() {
                return group_id;
        }
}
