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
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UOMCommandToUnitOfMeasure uomConverter;
	
	public IngredientCommandToIngredient(UOMCommandToUnitOfMeasure uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

	@Override
	@Nullable
	@Synchronized
	public Ingredient convert(IngredientCommand value) {
		// TODO Auto-generated method stub
		
		if(null == value) {
			return null;
		}
		
		final Ingredient ingredient = new Ingredient();
		
		ingredient.setId(value.getId());
		ingredient.setAmount(value.getAmount());
		ingredient.setDescription(value.getDescription());
		ingredient.setUom(uomConverter.convert(value.getUom()));
	
		return ingredient;
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
