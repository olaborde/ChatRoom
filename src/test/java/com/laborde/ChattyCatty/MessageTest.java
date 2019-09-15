package com.laborde.ChattyCatty;


import com.laborde.ChattyCatty.chat.Message;
import com.laborde.ChattyCatty.chat.WebSocketChatServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatServer.class)
public class MessageTest     {

    private Message message;
    private WebSocketChatServer webSocketChatServer;

    @Test
    public void testMessage(){
        String username = "John Doe";
       Message myMessage = new Message("SPEAK", username, "Hello World!", 1 );


        assertEquals(myMessage.getType(),"SPEAK" );

    }
}
