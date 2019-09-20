package com.laborde.ChattyCatty.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(ChatController.class)
public class ChattyCattyApplicationTests {



	@Autowired
	private ChatController chatController;

//	@Test
//	public void getLogin() throws Exception{
//		mockMvc.perform(get("/"))
//				.andExpect(status().isOk());
//		assert(true);
//	}

	@Test
	public void contextLoads() {
		assertThat(chatController).isNotNull();
	}

}
