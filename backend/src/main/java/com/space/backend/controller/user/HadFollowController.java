package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.HadFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HadFollowController {

    @Autowired
    private HadFollowService hadFollowService;

    @PostMapping("/api/user/hadfollow/")
    public JSONObject hadFollow(@RequestParam Map<String, String> data){
        Integer user1Id = Integer.parseInt(data.get("user1Id"));
        Integer user2Id = Integer.parseInt(data.get("user2Id"));
        return hadFollowService.hadFollow(user1Id, user2Id);
    }
}
