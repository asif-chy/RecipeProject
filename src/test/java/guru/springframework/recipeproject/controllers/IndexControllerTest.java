package guru.springframework.recipeproject.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.services.RecipeService;

class IndexControllerTest {
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	IndexController controller;
	
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		controller = new IndexController(recipeService);
		
	}
	
	@Test
	public void MockMVC() throws Exception {
		
		setUp();
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	void testGetIndexPage() {
		
		setUp();
		
		//Given
		Set<Recipe> recipes = new HashSet<>();
		/*
		 * recipes.add(new Recipe());
		 * 
		 * Recipe recipe = new Recipe(); recipe.setId(4L); recipes.add(recipe);
		 */
		
		when(recipeService.getRecipe()).thenReturn(recipes);

		//ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		//When
		String viewName = controller.getIndexPage(model);
		
		//Then
		assertEquals("index",viewName);
		
		verify(recipeService,times(1)).getRecipe();
		verify(model,times(1)).addAttribute("recipes", recipeService.getRecipe());
		
		/*
		 * Set<Recipe> setInController = argumentCaptor.getValue();
		 * assertEquals(2,setInController.size());
		 */
		
	}

}
