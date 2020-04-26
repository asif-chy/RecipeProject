package guru.springframework.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipeproject.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
