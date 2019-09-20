package com.laborde.ChattyCatty;


import com.laborde.ChattyCatty.web.ChatController;
import com.laborde.ChattyCatty.web.Message;
import com.laborde.ChattyCatty.web.WebSocketChatServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatController.class)
public class MessageTest     {

    private Message message;
    private WebSocketChatServer webSocketChatServer;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMessage(){
        String username = "John Doe";
       Message myMessage = new Message("SPEAK", username, "Hello World!", 1 );


        assertEquals(myMessage.getType(),"SPEAK" );

    }
    //  Check if the message is displayed on the chat.
//
//    @Test
//    public void messageDisplayed(){
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9000/chat?username=Jane"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath(""))
//
//    }
}
