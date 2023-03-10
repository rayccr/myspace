package com.space.backend.controller.user;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.user.UpdateSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateSignServiceController {
    @Autowired
    private UpdateSignService updateSignService;

    @PostMapping("/api/user/updatesign/")
    public JSONObject updateSign(@RequestParam Map<String, String> data){
        Integer uerId = Integer.parseInt(data.get("userId"));
        String content = data.get("content");
        return updateSignService.updateSign(uerId, content);
    }
}
