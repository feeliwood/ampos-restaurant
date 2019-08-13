package ampos.restaurant.models;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuRequest {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private List<String> details;

    public MenuRequest( Long id, String name, String description, String imageUrl, BigDecimal price, List<String> details ) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.details = details;
    }
}
