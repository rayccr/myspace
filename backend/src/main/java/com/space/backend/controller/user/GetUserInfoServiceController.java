package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class GetUserInfoServiceController {

    @Autowired
    private GetUserInfoService getUserInfoService;

    @PostMapping("/api/user/getuserinfo/")
    public JSONObject getUserInfo(@RequestParam Map<String, String>data){
        Integer userId = parseInt(data.get("userId"));
        return getUserInfoService.getUserInfo(userId);
    }

    @PostMapping("/api/user/getuserinfobyname/")
    public JSONObject getUserInfoByName(@RequestParam Map<String, String>data){
        String username = data.get("username");
        return getUserInfoService.getUserInfoByName(username);
    }
}
