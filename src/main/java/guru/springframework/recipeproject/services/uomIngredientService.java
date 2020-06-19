package guru.springframework.recipeproject.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipeproject.command.UOMCommand;

@Service
public interface uomIngredientService {
	
	public Set<UOMCommand> viewUomList();

}
