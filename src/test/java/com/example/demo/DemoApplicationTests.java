package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(Controller.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET /test return Hello, World!")
	void getTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello, World!");
	}

}
