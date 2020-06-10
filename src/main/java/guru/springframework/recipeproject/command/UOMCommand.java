package guru.springframework.recipeproject.command;

public class UOMCommand {
	
	private Long id;
	private String description;
	
	public UOMCommand() {
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

}
