package guru.springframework.recipeproject.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.IngredientCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngrediantToIngrediantCommand implements Converter<Ingredient, IngredientCommand>{
	
	private final UnitOfMeasureToUOMCommand unitOfMeasureToUOMCommand;

	public IngrediantToIngrediantCommand(UnitOfMeasureToUOMCommand unitOfMeasureToUOMCommand) {
		super();
		this.unitOfMeasureToUOMCommand = unitOfMeasureToUOMCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient value) {
		// TODO Auto-generated method stub
		
		if(null != value) {
			
			final IngredientCommand ingredientCommand = new IngredientCommand();
			
			ingredientCommand.setId(value.getId());
			if(null != value.getRecipe().getId()) {
			ingredientCommand.setRecipeId(value.getRecipe().getId());
			}
			ingredientCommand.setDescription(value.getDescription());
			ingredientCommand.setAmount(value.getAmount());
			ingredientCommand.setUom(unitOfMeasureToUOMCommand.convert(value.getUom()));
			
			return ingredientCommand;
			
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
