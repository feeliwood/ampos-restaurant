package ampos.restaurant.domain.dto;

import ampos.restaurant.util.BillItemRequestView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Getter
@Setter
@NoArgsConstructor
public class BillItemRequestDTO {

    @JsonView( BillItemRequestView.Edit.class)
    private Long id;

    @JsonView( BillItemRequestView.Add.class)
    private int quantity;

    @JsonView( BillItemRequestView.Add.class)
    @NotNull
    private Long menuItemId;
}
