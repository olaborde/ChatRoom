package com.laborde.ChattyCatty.chat;

import com.laborde.ChattyCatty.chat.ChatController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChattyCattyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ChatController chatController;

	@Test
	public void getLogin() throws Exception{
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
		assert(true);
	}

//	@Test
//	public void contextLoads() {
//		assertThat(chatController).isNotNull();
//	}

}
