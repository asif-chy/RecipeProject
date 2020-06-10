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
public class UOMCommandToUnitOfMeasure implements Converter<UOMCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UOMCommand value) {
		// TODO Auto-generated method stub
		
		if(null == value) {
			return null;
		}
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(value.getId());
		uom.setDescription(value.getDescription());
		
		return uom;
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
