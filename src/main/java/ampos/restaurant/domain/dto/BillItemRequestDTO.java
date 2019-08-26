package ampos.restaurant.domain.dto;

import ampos.restaurant.util.Views;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Getter
@Setter
@NoArgsConstructor
public class BillItemRequestDTO {

    @JsonView( Views.Internal.class)
    private Long id;

    @JsonView( Views.Public.class)
    private int quantity;

    @JsonView( Views.Public.class)
    @NotNull
    private Long menuItemId;

    @JsonView( Views.Public.class)
    @NotNull
    private Long billId;
}
