package com.space.backend.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.space.backend.consumer.utils.JwtAuthentication;
import com.space.backend.mapper.UserMapper;
import com.space.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {

    final private static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>(); // userId, WebSocket

    private User user;
    private Session session = null;

    private static UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        System.out.println("connected!");

        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);

        if(this.user != null){
            users.put(userId, this);
        } else {
            this.session.close();
        }
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("disconnected!");
        if(this.user != null){
            users.remove(this.user.getId());
        }
    }


    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // 从Client接收消息
        System.out.println("receive message!");

        JSONObject data = JSONObject.parseObject(message); // 解析前端传来的东西,一般可以当路由

        String username = data.getString("username");
        String content = data.getString("content");

        sendMessageToAll(message);
    }

    public void sendMessageToAll(String message){
        // 遍历集合，可设置为推送给指定sid，为 null 时发送给所有人
        Iterator entrys = users.entrySet().iterator();

        while (entrys.hasNext()) {
            Map.Entry entry = (Map.Entry) entrys.next();
            if (user.getId() != entry.getKey()) {
                users.get(entry.getKey()).sendMessage(message);
            }
        }
    }

    public void sendMessage(String message){
        synchronized (this.session){
            try{
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}

