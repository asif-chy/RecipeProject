package guru.springframework.recipeproject.command;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import guru.springframework.recipeproject.domain.Difficulty;

public class RecipeCommand {
	
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String description;
	
	@Min(1)
	@Max(999)
	private int prepTime;
	
	@Min(1)
	@Max(999)
	private int cookTime;
	
	
	private int servings;
	private String source;
	
	@URL
	private String url;
	
	@NotBlank
	private String directions;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private Set<CategoryCommand> categories = new HashSet<>();
	private NotesCommand notes;
	
	@Lob
	private Byte[] image;
	
	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public NotesCommand getNotes() {
		return notes;
	}

	public void setNotes(NotesCommand notes) {
		this.notes = notes;
	}

	public RecipeCommand() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Set<IngredientCommand> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientCommand> ingredients) {
		this.ingredients = ingredients;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Set<CategoryCommand> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryCommand> categories) {
		this.categories = categories;
	}
	
	

}
