package guru.springframework.recipeproject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import guru.springframework.recipeproject.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	Optional<Category> findByDescription(String Description);

}
