package com.laborde.chattycatty;

import com.laborde.chattycatty.chat.ChatController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChattycattyApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ChatController login;

	@org.testng.annotations.Test
	public void login() throws Exception {
		String username = "John";


		this.mockMvc.perform(get("/"))
				.andExpect(xpath("//textarea[@name='" + username + "']").exists());
//        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//                .andExpect(view() .name("/login"));
		MockHttpServletRequestBuilder createMessage = post("/")
				.param(username, "John");


		mockMvc.perform(createMessage)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost:9000/chat?username=john"));
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(login).isNotNull();
	}

}
