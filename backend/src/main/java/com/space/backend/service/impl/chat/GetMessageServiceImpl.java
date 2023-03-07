package com.space.backend.service.impl.chat;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.space.backend.mapper.ChatMapper;
import com.space.backend.pojo.Chat;
import com.space.backend.service.chat.GetMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMessageServiceImpl implements GetMessageService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public JSONObject getMessage() {
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();

        queryWrapper.ne("user_id", -1); // 用户名称为-1, 即是选所有.

        List<Chat> blocks = chatMapper.selectList(queryWrapper);

        JSONObject resp = new JSONObject();
        resp.put("blocks", blocks);

        return resp;
    }
}
