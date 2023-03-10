package com.space.backend.controller.chatspace;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.service.chat.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetMessageController {
    @Autowired
    private GetMessageService getMessageService;

    @PostMapping("/api/chat/getmessage/")
    public JSONObject getMessage(@RequestParam Map<String, String> map){
        return getMessageService.getMessage();
    }
}
