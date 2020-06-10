package guru.springframework.recipeproject.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.CategoryCommand;
import guru.springframework.recipeproject.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	@Override
	public Category convert(CategoryCommand value) {
		
		if(null == value) {
			return null;
		}
		
		final Category category = new Category();
		
		category.setId(value.getId());
		category.setDescription(value.getDescription());
		// TODO Auto-generated method stub
		return category;
		
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
