package guru.springframework.recipeproject.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.recipeproject.command.UOMCommand;
import guru.springframework.recipeproject.converters.UnitOfMeasureToUOMCommand;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;

@Service
public class uomIngredientServiceImpl implements uomIngredientService {

	private final UnitOfMeasureRepository uomRepository;
	private final UnitOfMeasureToUOMCommand uomToUOMCommand;

	public uomIngredientServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUOMCommand uomToUOMCommand) {
		super();
		this.uomRepository = uomRepository;
		this.uomToUOMCommand = uomToUOMCommand;
	}

	@Override
	public Set<UOMCommand> viewUomList() {
		// TODO Auto-generated method stub

		// Set<UnitOfMeasure> unitOfMeasure = (Set<UnitOfMeasure>)
		// uomRepository.findAll();
		
		//Need to understand/breakdown the code

		return StreamSupport.stream(uomRepository.findAll().spliterator(), false).map(uomToUOMCommand::convert)
				.collect(Collectors.toSet());

	}

}