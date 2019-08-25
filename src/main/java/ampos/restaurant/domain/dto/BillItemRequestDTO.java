package ampos.restaurant.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BillItemRequestDTO {
    private int quantity;

    @NotNull
    private Long menuItemId;

    @NotNull
    private Long billId;
}
