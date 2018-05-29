package com.harry.model;

import java.util.UUID;

public class UserInfo {

    private String apiKey = "565d3d666757456081a206fe46514283";

    private String userId = UUID.randomUUID().toString().replaceAll("-","");

    private String groupId;

    private String userIdName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserIdName() {
        return userIdName;
    }

    public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
