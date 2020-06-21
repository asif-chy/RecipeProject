package guru.springframework.recipeproject.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	private final CategoryCommandToCategory categoryCommandConverter;
	private final IngredientCommandToIngredient ingredientCommandConverter;
	private final NotesCommandToNotes notesCommandConverter;
	
	public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandConverter,
			IngredientCommandToIngredient ingredientCommandConverter, NotesCommandToNotes notesCommandConverter) {
		super();
		this.categoryCommandConverter = categoryCommandConverter;
		this.ingredientCommandConverter = ingredientCommandConverter;
		this.notesCommandConverter = notesCommandConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand value) {
		// TODO Auto-generated method stub
		
		if(null == value) {
			return null;
		}
		
		final Recipe recipe = new Recipe();
		
		recipe.setId(value.getId());
        recipe.setCookTime(value.getCookTime());
        recipe.setPrepTime(value.getPrepTime());
        recipe.setDescription(value.getDescription());
        recipe.setDifficulty(value.getDifficulty());
        recipe.setDirections(value.getDirections());
        recipe.setServings(value.getServings());
        recipe.setSource(value.getSource());
        recipe.setUrl(value.getUrl());
        recipe.setNotes(notesCommandConverter.convert(value.getNotes()));
        recipe.setImage(value.getImage());
        
        //copy pasted //need to go over to understand the lambda
        if (value.getCategories() != null && value.getCategories().size() > 0){
            value.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryCommandConverter.convert(category)));
        }

        if (value.getIngredients() != null && value.getIngredients().size() > 0){
            value.getIngredients()
                    .forEach(ingredient -> recipe.getIngrediant().add(ingredientCommandConverter.convert(ingredient)));
        }
        
        return recipe;
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
