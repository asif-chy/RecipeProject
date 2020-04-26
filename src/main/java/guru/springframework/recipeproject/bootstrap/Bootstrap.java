package guru.springframework.recipeproject.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipeproject.domain.Category;
import guru.springframework.recipeproject.domain.Difficulty;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Notes;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.repositories.CategoryRepository;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	
	private CategoryRepository categoryRespository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	private RecipeRepository recipeRepository;

	public Bootstrap(CategoryRepository categoryRespository, UnitOfMeasureRepository unitOfMeasureRepository,
			RecipeRepository recipeRepository) {
		super();
		this.categoryRespository = categoryRespository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.recipeRepository = recipeRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		recipeRepository.saveAll(getRecipe());
		
	}
	
	

	private List<Recipe> getRecipe() {

		List<Recipe> recipes = new ArrayList<>(2);

		// Find UOM

		Optional<UnitOfMeasure> cupUOMOptional = unitOfMeasureRepository.findByDescription("Cup");

		if (!cupUOMOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}

		Optional<UnitOfMeasure> teaspoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

		if (!teaspoonUOMOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}

		Optional<UnitOfMeasure> tablespoonUOMOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

		if (!tablespoonUOMOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}

		Optional<UnitOfMeasure> pinchUOMOptional = unitOfMeasureRepository.findByDescription("Pinch");

		if (!pinchUOMOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}

		Optional<UnitOfMeasure> ounceUOMOptional = unitOfMeasureRepository.findByDescription("Ounce");

		if (!ounceUOMOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not found!");
		}
		
		UnitOfMeasure teaSpoon = teaspoonUOMOptional.get();
		UnitOfMeasure cup = cupUOMOptional.get();
		UnitOfMeasure tableSpoon = tablespoonUOMOptional.get();
		UnitOfMeasure pinch = pinchUOMOptional.get();
		UnitOfMeasure ounce = ounceUOMOptional.get();

		//// Find Category
		Optional<Category> americanCategory = categoryRespository.findByDescription("American");

		if (!americanCategory.isPresent()) {
			throw new RuntimeException("Expected Category not found!");
		}

		Optional<Category> italianCategory = categoryRespository.findByDescription("Italian");

		if (!italianCategory.isPresent()) {
			throw new RuntimeException("Expected Category not found!");
		}

		Optional<Category> indianCategory = categoryRespository.findByDescription("Indian");

		if (!indianCategory.isPresent()) {
			throw new RuntimeException("Expected Category not found!");
		}

		Optional<Category> chineseCategory = categoryRespository.findByDescription("Chinese");

		if (!chineseCategory.isPresent()) {
			throw new RuntimeException("Expected Category not found!");
		}
		
		Category american = americanCategory.get();
		Category indian = indianCategory.get();
		
		//Recipe
		
		Recipe desiOmelette = new Recipe();
		
		desiOmelette.setDescription("Asiful's Special Desi Omelette");
		desiOmelette.setPrepTime(10);
		desiOmelette.setCookTime(10);
		desiOmelette.setDifficulty(Difficulty.EASY);
		desiOmelette.setDirections("Slice 2 Green Pepper, and 1 Onion. Sear the sliced Green Peppper"
				+ " and Onion in 2 teaspoon of oil for 5 mins. Add Mustard Oil"
				+ " Crack open 3 eggs and mix them with the seared vegetables"
				+ " right away. Let the mix rest on pan for 1-2 min before flipping."
				+ " Let it rest another 1-2 min on the pan after flipping. Walla!");
		
		Notes desiOmeletteNotes = new Notes();
		desiOmeletteNotes.setRecipeNotes("Enjoy Them Desi Omelettes :)");
		
		desiOmeletteNotes.setRecipe(desiOmelette);
		desiOmelette.setNotes(desiOmeletteNotes);
		
		desiOmelette.getIngrediant().add(new Ingredient("Musturd Oil",new BigDecimal(2),teaSpoon, desiOmelette ));
		
		desiOmelette.getIngrediant().add(new Ingredient("Green Pepper",new BigDecimal(2),cup, desiOmelette));
		desiOmelette.getIngrediant().add(new Ingredient("Onion",new BigDecimal(1),cup, desiOmelette));
		desiOmelette.getIngrediant().add(new Ingredient("Egg",new BigDecimal(1),cup,desiOmelette)); 
		desiOmelette.getIngrediant().add(new Ingredient("Oil",new BigDecimal(2),tableSpoon, desiOmelette));
		 
		
		desiOmelette.getCategories().add(indian);
		
		recipes.add(desiOmelette);
		
		return recipes;
	}







}
