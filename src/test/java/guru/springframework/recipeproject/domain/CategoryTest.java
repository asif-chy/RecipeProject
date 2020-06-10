package guru.springframework.recipeproject.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class CategoryTest {
	
	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}

	@Test
	public void testGetId() {
		
		setUp();
		
		Long idValue = 4L;
		
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
		

	}

	@Test
	void testGetDescription() {

	}

	@Test
	void testGetRecipe() {
		
	}

}
