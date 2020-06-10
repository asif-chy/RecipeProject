package guru.springframework.recipeproject.converters;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import guru.springframework.recipeproject.command.NotesCommand;
import guru.springframework.recipeproject.domain.Notes;
import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

	@Synchronized
	@Nullable
	@Override
	public NotesCommand convert(Notes value) {
		// TODO Auto-generated method stub
		
		if(null != value) {
			
			final NotesCommand notesCommand = new NotesCommand();
			
			notesCommand.setId(value.getId());
			notesCommand.setRecipeNotes(value.getRecipeNotes());
			
			return notesCommand;
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
