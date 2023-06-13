package com.space.backend.controller.chat;

import com.space.backend.service.chat.AddMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddMessageController {

    @Autowired
    private AddMessageService addMessageService;

    @PostMapping("/api/chat/addmessage/")
    public Map<String, String> addMessage(@RequestParam Map<String, String>map) {
        String username = map.get("username");
        String content = map.get("content");
        return addMessageService.addMessage(username, content);
    }
}
