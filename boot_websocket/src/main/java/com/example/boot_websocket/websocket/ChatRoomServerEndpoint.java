package com.example.boot_websocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.example.boot_websocket.utils.WebSocketUtils.LIVING_SESSIONS_CACHE;
import static com.example.boot_websocket.utils.WebSocketUtils.sendMessage;
import static com.example.boot_websocket.utils.WebSocketUtils.sendMessageAll;

/**
 *  聊天室
 *
 * @author Dusk
 */
@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    private static final Logger log = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    /**\
     *  opopen 建立WebSocket 连接时触发
     *
     * @param username
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session){
        LIVING_SESSIONS_CACHE.put(username, session);

        String message = "欢迎用户["+username+"]来到聊天室！";
        log.info(message);
        sendMessageAll(message);
    }

    /**
     *  message 客户端监听端事件，当服务端向客户端推送消息时会被监听到
     *
     * @param username
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message){
        log.info(message);
        sendMessageAll("用户["+username+"]:"+message);
    }

    /**
     *  close 关闭websocket连接时触发
     *
     * @param username
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("username") String username, Session session){
        // 当前Session 移除
        LIVING_SESSIONS_CACHE.remove(username);
        //通知其他人当前用户已经离开聊天室
        sendMessageAll("用户["+ username +"]已经离开聊天室");

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  关闭websocket 连接时触发
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable){
        try{
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    @GetMapping("/chat-room/{sender}/to/{receive}")
    public void onMessage(@PathVariable("sender") String send, @PathVariable("receive") String receive, String message){
        sendMessage(LIVING_SESSIONS_CACHE.get(receive), "["+send+"]"+"-> ["+receive+"]:"+message);
    }
}
