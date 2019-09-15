package com.laborde.ChattyCatty.chat;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat/{username}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class WebSocketChatServer {


    /**
     * All chat sessions.
     */
    private Session session;
    // This hashmap store all the new session for web socket client
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message) throws IOException, EncodeException{
        for (Session sess: onlineSessions.values()) {
            if (sess.isOpen()) { sess.getBasicRemote().sendObject(message); } }

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        this.session = session;
        onlineSessions.put(session.getId(), session);
        Message message = new Message( "SPEAK", username, "Joined", onlineSessions.size());
        sendMessageToAll(message);


    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    // Java objects to JSON --> toJSONString
    // JSON to Java objects ==> parseObject
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws EncodeException, IOException {

        Message message = new Message();
        JSONObject text = JSON.parseObject(jsonStr);

        message.setType("SPEAK");
        message.setUsername(text.getString("username"));
        message.setMsg(text.getString("msg"));
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
        try {
            onlineSessions.remove(session);

            Message message = new Message();
            message.setOnlineCount(onlineSessions.size()-1);

            session.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }

    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }



}