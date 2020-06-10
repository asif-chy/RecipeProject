package guru.springframework.recipeproject.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.NotesCommand;
import guru.springframework.recipeproject.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Override
	public Notes convert(NotesCommand value) {
		// TODO Auto-generated method stub
		
		if(null == value) {
			return null;
		}
		
		final Notes notes = new Notes();
		
		notes.setId(value.getId());
		notes.setRecipeNotes(value.getRecipeNotes());
		
		return notes;
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
