package ampos.restaurant.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class BillRequestDTO {
    private Set<BillItemRequestDTO> billItems = new HashSet<>();
}
