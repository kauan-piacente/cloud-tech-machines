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

	// Cadastrar maquina
	@Test
	void testeCadastrarMaquina() throws Exception{
		String machine = """
		{
			"name": "Machine 3",
			"type": "Extrusora",
			"sector": "Produção",
			"status": "Operando",
		}
		""";

		mockMvc.perform(post("machines/register")
		.contentType("application/sjon")
		.content(machine))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Machine 3"))
		.andExpect(jsonPath("$.type").value("Extrusora"))
		.andExpect(jsonPath("$.sector").value("Produção"))
		.andExpect(jsonPath("$.status").value("Operando")); 
	}

	// Atualizar maquina
	@Test
	void testeAtualizarMaquina() throws Exception{
		String livroAtualizado = """
			{
				"name": "Machine 2 atualizado",
				"type": "Extrusora aA",
				"sector": "Produção teste",
				"status": "Operando",
			}
			""";

			mockMvc.perform(put("machines/89bc351b-e3b5-4e74-8dfd-2bf1db747584")
			.contentType("application/sjon")
			.content(livroAtualizado))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(2))
			.andExpect(jsonPath("$.name").value("Machine 2 atualizado"))
			.andExpect(jsonPath("$.type").value("Extrusora aA"))
			.andExpect(jsonPath("$.sector").value("Produção teste"))
			.andExpect(jsonPath("$.status").value("Operando")); 
	}

}
