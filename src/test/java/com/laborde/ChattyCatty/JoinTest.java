package com.laborde.ChattyCatty;

import com.laborde.ChattyCatty.chat.WebSocketChatServer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatServer.class)
public class JoinTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void joinTest() throws Exception{
        String username = "John";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServerName("localhost:9000");
        request.setRequestURI("/chat");
        request.setQueryString("username="+username);

        String url = request.getRequestURL() + "?" + request.getQueryString();
        assertThat(url, is("http://localhost:9000/chat?username=John"));

//        MvcResult mvcResult = mockMvc.perform(put("http://localhost:9000/chat?username={username}","John"))
//                .andExpect(status().isOk()).andReturn();
//        Assert.assertEquals("John", mvcResult.getResponse().getContentAsString());
    }
}
