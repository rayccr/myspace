package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.FollowQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FollowQueryController {

    @Autowired
    private FollowQueryService followQueryService;

    @PostMapping("/api/user/whoifollowed/")
    public JSONObject whoIFollowed(@RequestParam Map<String, String> data){
        Integer userId = Integer.parseInt(data.get("userId"));
        return followQueryService.whoIFollowed(userId);
    }

    @PostMapping("/api/user/whofollowedme/")
    public JSONObject whoFollowedMe(@RequestParam Map<String, String> data){
        Integer userId = Integer.parseInt(data.get("userId"));
        return followQueryService.whoFollowedMe(userId);
    }

}
