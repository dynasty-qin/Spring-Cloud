package com.harry.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserInfo {

    private String apiKey = "565d3d666757456081a206fe46514283";

    private String userId = UUID.randomUUID().toString().replaceAll("-","");

    private String groupId;

    private String userIdName;
}
