package guru.springframework.recipeproject.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.services.RecipeService;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	RecipeController controller;
	
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		controller = new RecipeController(recipeService);
		
	}
	@Test
	void testShowById() throws Exception {
		
		setUp();
		
		Recipe recipe = new Recipe();
		
		recipe.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		when(recipeService.findById(anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));
		
		//fail("Not yet implemented");
	}

}
