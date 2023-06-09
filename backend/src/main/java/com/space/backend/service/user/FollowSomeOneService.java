package com.space.backend.service.user;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface FollowSomeOneService {
    JSONObject followSomeOne(Integer user1Id, Integer user2Id);
}
