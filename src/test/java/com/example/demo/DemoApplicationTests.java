package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
class DemoApplicationTests {
	Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private final static Controller.Dto dto_ok =
			new Controller.Dto("The Java Logo", "What is depicted on the Java logo?",
			Arrays.asList("Robot", "Tea leaf", "Cup of coffee", "Bug"),
			List.of(2));

	private final static Controller.Dto dto_bad_request_data_not_valid1 =
			new Controller.Dto("", "What is depicted on the Java logo?",
			Arrays.asList("Robot", "Tea leaf", "Cup of coffee", "Bug"),
			List.of(2));

	private final static Controller.Dto dto_bad_request_data_not_valid2 =
			new Controller.Dto("The Java Logo", null,
					Arrays.asList(null, "Tea leaf", "Cup of coffee", "Bug"),
					List.of(2));
	private final static Controller.Dto dto_bad_request_data_not_valid3 =
			new Controller.Dto("The Java Logo", "What is depicted on the Java logo?",
					Arrays.asList("Robot"),
					List.of(2));
	@Test
	@DisplayName(Controller.addQuiz + " test statuses")
	void getTest() throws Exception {
		ResultActions result = perform(dto_ok);
		result.andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.title").value("The Java Logo"));

		result = perform(dto_bad_request_data_not_valid1);
		result.andExpect(status().isBadRequest());

		result = perform(dto_bad_request_data_not_valid2);
		result.andExpect(status().isBadRequest());

		result = perform(dto_bad_request_data_not_valid3);
		result.andExpect(status().isBadRequest());

	}

	private ResultActions perform(Controller.Dto dto) throws Exception {
		return mockMvc.perform(post(Controller.addQuiz).
				contentType(MediaType.APPLICATION_JSON).
				content(objectMapper.writeValueAsString(dto)));
	}

}
