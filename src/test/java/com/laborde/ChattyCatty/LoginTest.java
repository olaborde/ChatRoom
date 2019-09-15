package com.laborde.ChattyCatty;


import com.laborde.ChattyCatty.chat.ChatController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatController.class)
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLogin() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
        assert(true);
    }


}
