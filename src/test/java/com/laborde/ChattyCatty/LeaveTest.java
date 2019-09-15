package com.laborde.ChattyCatty;

import com.laborde.ChattyCatty.chat.ChatController;
import com.laborde.ChattyCatty.chat.WebSocketChatServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatServer.class)
public class LeaveTest {

    private MockMvc mockMvc;
    private ChatController chatController;

    @Before
    public void setup(){
        chatController = new ChatController();
        WebApplicationContext context;
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();

    }

    @Test
    public void testLeave() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
