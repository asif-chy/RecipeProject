package guru.springframework.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipeproject.command.IngredientCommand;
import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.command.UOMCommand;
import guru.springframework.recipeproject.services.IngredientService;
import guru.springframework.recipeproject.services.RecipeService;
import guru.springframework.recipeproject.services.uomIngredientService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final uomIngredientService uomIngredientService;
	
	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			guru.springframework.recipeproject.services.uomIngredientService uomIngredientService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.uomIngredientService = uomIngredientService;
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String viewIngredientList(@PathVariable String recipeId, Model model) {
		
		log.debug("viewIngredientList: Populate Ingredient List");
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		
		return("recipe/ingredient/ingredient");
		
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		
		model.addAttribute("ingredient", ingredientService.findByIngredientIdRecipeId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		return "recipe/ingredient/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		
		log.debug("udpateIngredient");
		log.debug("Recipe id " + recipeId);
		log.debug("Ingredient id" + id);
		
		model.addAttribute("ingredient", ingredientService.findByIngredientIdRecipeId(Long.valueOf(recipeId), Long.valueOf(id)));
		
		model.addAttribute("uomList", uomIngredientService.viewUomList());
		
		return "recipe/ingredient/ingredientForm";
		
	}
	
	@PostMapping
	@RequestMapping("recipe/{recipeId}/ingredient")
	public String saveIngredient(@ModelAttribute IngredientCommand command) {
		
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
		
		log.debug("saveIngredient");
		log.debug("Recipe id " + savedCommand.getRecipeId());
		log.debug("Ingredient id" + savedCommand.getId());
		
		return("redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show");
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/new")
	public String newIngredient(@PathVariable String recipeId, Model model) {
		
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		
		model.addAttribute("ingredient", ingredientCommand);
		ingredientCommand.setUom(new UOMCommand());
		
		model.addAttribute("uomList", uomIngredientService.viewUomList());
		
		return "recipe/ingredient/ingredientForm";
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
		
		log.debug("deleteIngredient");
		log.debug("Ingredient ID " + id);
		
		ingredientService.deleteIngredientById(Long.valueOf(recipeId), Long.valueOf(id));
		
		return "redirect:/recipe/" +recipeId+"/ingredients";
	}
}
