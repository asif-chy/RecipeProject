package guru.springframework.recipeproject.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.CategoryCommand;
import guru.springframework.recipeproject.domain.Category;
import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category value) {
		// TODO Auto-generated method stub
		
		if(null == value) {
			return null;
		}
		
		final CategoryCommand categoryCommand = new CategoryCommand();
		
		categoryCommand.setId(value.getId());
		categoryCommand.setDescription(value.getDescription());
		
		return categoryCommand;
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
