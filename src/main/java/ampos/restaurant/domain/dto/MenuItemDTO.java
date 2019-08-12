package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;


/**
 * A DTO for the MenuItem entity.
 */
public class MenuItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String imageUrl;

    @NotNull
    private BigDecimal price;

    private List<String> details;
    

    public MenuItemDTO() {
		super();
	}

    public MenuItemDTO(Long id, @NotNull String name, @NotNull String description, String imageUrl,
		    @NotNull BigDecimal price, List<String> details) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.imageUrl = imageUrl;
	this.price = price;
	this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails( List<String> details ) {
        this.details = details;
    }
}
