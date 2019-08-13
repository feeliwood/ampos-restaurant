package ampos.restaurant.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillResponse {
    private Long id;
    private Set<BillItemsResponse> billItems = new HashSet<>();
    private BigDecimal total;
}
