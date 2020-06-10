package guru.springframework.recipeproject.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.UOMCommand;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureToUOMCommand implements Converter<UnitOfMeasure, UOMCommand>{

	
	@Synchronized
	@Nullable
	@Override
	public UOMCommand convert(UnitOfMeasure value) {
		// TODO Auto-generated method stub
		
		if(null != value) {
			
			final UOMCommand uomCommand = new UOMCommand();
			
			uomCommand.setId(value.getId());
			uomCommand.setDescription(value.getDescription());
			
			return uomCommand;
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
