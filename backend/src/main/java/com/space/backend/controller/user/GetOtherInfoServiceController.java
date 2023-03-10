package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.GetOtherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
public class GetOtherInfoServiceController {

    @Autowired
    private GetOtherInfoService getOtherInfoService;

    @PostMapping("/api/user/getotherinfo/")
    public JSONObject getOtherInfo(@RequestParam Map<String, String>data){
        Integer userId = parseInt(data.get("userId"));
        return getOtherInfoService.getOtherInfo(userId);
    }
}
