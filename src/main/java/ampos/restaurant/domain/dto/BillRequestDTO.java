package ampos.restaurant.domain.dto;

import ampos.restaurant.util.Views;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

@Setter
@Getter
public class BillRequestDTO {
    @JsonView( Views.Public.class)
    private Set<BillItemRequestDTO> billItems = new HashSet<>();
}
