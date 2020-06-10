package guru.springframework.recipeproject.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.domain.Category;
import guru.springframework.recipeproject.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{
	
	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final IngrediantToIngrediantCommand ingredientToIngredientCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	
	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand,
			IngrediantToIngrediantCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand) {
		super();
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.notesToNotesCommand = notesToNotesCommand;
	}

	@Override
	public RecipeCommand convert(Recipe value) {
		// TODO Auto-generated method stub
		
		if (null != value) {
			
			final RecipeCommand recipeCommand = new RecipeCommand();
			
			recipeCommand.setId(value.getId());
			recipeCommand.setDescription(value.getDescription());
			recipeCommand.setDirections(value.getDirections());
			recipeCommand.setDifficulty(value.getDifficulty());
			recipeCommand.setPrepTime(value.getPrepTime());
			recipeCommand.setCookTime(value.getCookTime());
			recipeCommand.setServings(value.getServings());
			recipeCommand.setSource(value.getSource());
			recipeCommand.setUrl(value.getUrl());
			recipeCommand.setNotes(notesToNotesCommand.convert(value.getNotes()));
			
	        if (value.getCategories() != null && value.getCategories().size() > 0){
	            value.getCategories()
	                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
	        }

	        if (value.getIngrediant() != null && value.getIngrediant().size() > 0){
	            value.getIngrediant()
	                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
	        }
	        
	        return recipeCommand;
		}
		return null;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
