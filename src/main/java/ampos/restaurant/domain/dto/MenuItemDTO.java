package ampos.restaurant.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the MenuItem entity.
 */
@Getter
@Setter
@NoArgsConstructor
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

    public MenuItemDTO( Long id, @NotNull String name, @NotNull String description, String imageUrl, @NotNull BigDecimal price, List<String> details ) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.details = details;
    }
}
