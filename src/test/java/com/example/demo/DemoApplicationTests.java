package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName(Controller.addQuiz + " test statuses")
	void getTest() throws Exception {
		Controller.Dto dto = new Controller.Dto("The Java Logo", "What is depicted on the Java logo?",
				Arrays.asList("Robot", "Tea leaf", "Cup of coffee", "Bug"),
                List.of(2));

		mockMvc.perform(post(Controller.addQuiz).
				contentType(MediaType.APPLICATION_JSON).
				content(objectMapper.writeValueAsString(dto))).
				andExpect(status().isOk());

		dto = new Controller.Dto("", "What is depicted on the Java logo?",
				Arrays.asList("Robot", "Tea leaf", "Cup of coffee", "Bug"),
				List.of(2));

		mockMvc.perform(post(Controller.addQuiz).
						contentType(MediaType.APPLICATION_JSON).
						content(objectMapper.writeValueAsString(dto))).
				andExpect(status().isBadRequest());


	}

}
