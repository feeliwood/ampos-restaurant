package ampos.restaurant.web.rest.vm;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class MenuItemRequestVM implements Serializable {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private String details;

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

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice( BigDecimal price ) {
	this.price = price;
    }

    public String getDetails() {
	return details;
    }

    public void setDetails( String details ) {
	this.details = details;
    }
}
