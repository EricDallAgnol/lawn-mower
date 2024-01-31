package com.lawn;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = LawnMowerSimulation.class)
@AutoConfigureMockMvc
class LawnMowerSimulationControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void runSimulationProvidedForOneLawnMower() throws Exception {
		String instructions = """
				5 5\s
				1 2 N\s
				GAGAGAGAA""";
		String expectedValue = "(1,3,N)\n";

		mockMvc.perform(
						post("/instructions")
								.contentType(MediaType.TEXT_XML_VALUE)
								.content(instructions)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(expectedValue));
	}

	@Test
	public void runSimulationProvidedSeveralLawnMower() throws Exception {
		String instructions = """
				5 5\s
				1 2 N\s
				GAGAGAGAA
				3 3 E\s
				AADAADADDA""";
		String expectedValue = "(1,3,N)\n(5,1,E)\n";

		mockMvc.perform(
						post("/instructions")
								.contentType(MediaType.TEXT_XML_VALUE)
								.content(instructions)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(expectedValue));
	}

}
