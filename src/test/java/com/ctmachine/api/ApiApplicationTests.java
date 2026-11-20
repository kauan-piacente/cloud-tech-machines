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

	// Listar livro por ID
	@Test
	void testarListarMaquinaPorId() throws Exception{
		mockMvc.perform(get("/machines/a32cb2b4-e8f9-4715-98a7-da9606de49cb"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value("a32cb2b4-e8f9-4715-98a7-da9606de49cb"))
		.andExpect(jsonPath("$name").value("Máquina 1"))
		.andExpect(jsonPath("$type").value("CNC"))
		.andExpect(jsonPath("$sector").value("Produção"))
		.andExpect(jsonPath("$status").value("Operando"));
	}

}
