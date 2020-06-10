package guru.springframework.recipeproject.command;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.recipeproject.domain.Difficulty;

public class RecipeCommand {
	
	private Long id;
	private String description;
	private int prepTime;
	private int cookTime;
	private int servings;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientCommand> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private Set<CategoryCommand> categories = new HashSet<>();
	private NotesCommand notes;
	
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
