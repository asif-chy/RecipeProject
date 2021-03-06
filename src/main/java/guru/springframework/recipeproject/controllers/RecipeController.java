package guru.springframework.recipeproject.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.exceptions.NotFoundException;
import guru.springframework.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	
	@GetMapping
	@RequestMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		System.out.println(id);
		
		Recipe recipe = recipeService.findById(Long.valueOf(id));
		
		System.out.println("RECIPE ID: " +  recipe.getId());
		
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		
		return "recipe/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/new")
	public String newRecipe(Model model) {
		
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	
	@PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){
		 
		if(bindingResult.hasErrors()) {
    	   
    	   bindingResult.getAllErrors().forEach(objectError -> {log.debug(objectError.toString());});
    	   return "recipe/recipeform";
    	   
       }
       
       RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

	@GetMapping
	@RequestMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/recipeform";
	}
	
	@GetMapping
	@RequestMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		
		log.debug("Delete Id: " + id);
		
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		
		log.error("Not Found Exception");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception);
		
		return modelAndView;
		
	}
	
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(NumberFormatException.class) public ModelAndView
	 * handleNumberFormat(Exception exception) {
	 * 
	 * log.error("Not Found Exception");
	 * 
	 * ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("400error"); modelAndView.addObject("exception",
	 * exception);
	 * 
	 * return modelAndView;
	 * 
	 * }
	 */
	

}
