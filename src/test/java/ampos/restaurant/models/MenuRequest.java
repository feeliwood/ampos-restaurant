package ampos.restaurant.models;

import java.math.BigDecimal;
import java.util.List;

public class MenuRequest {

    private String name;

    private String description;

    private String imageUrl;

    private BigDecimal price;

    private List<String> details;

	public MenuRequest(Long id, String name, String description, String imageUrl, BigDecimal price,
			List<String> details) {
		super();
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.details = details;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
    
    



}
