package guru.springframework.recipeproject.command;

import java.math.BigDecimal;

public class IngredientCommand {
	
	private Long id;
	private String description;
	private BigDecimal amount;
	private UOMCommand uom;
	
	public IngredientCommand() {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public UOMCommand getUom() {
		return uom;
	}

	public void setUom(UOMCommand uom) {
		this.uom = uom;
	}



}
