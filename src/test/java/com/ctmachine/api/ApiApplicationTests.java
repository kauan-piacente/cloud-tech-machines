package com.ctmachine.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ctmachine.api.controllers.ProductionController;

@WebMvcTest(ProductionController.class)
class ApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Listar maquinas
	@Test
	void testarListarMaquinas() throws Exception {
		mockMvc.perform(get("/machines"))
		.andExpect(status().isOk())	
		.andExpect(jsonPath("$.length()").value(2))
		.andExpect(jsonPath("$[0].name").value("Máquina 1"))
		.andExpect(jsonPath("$[0].name").value("Máquina 2"));
	}

}
