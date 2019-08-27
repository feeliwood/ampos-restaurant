package ampos.restaurant.domain.dto;

import ampos.restaurant.util.BillItemRequestView;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

@Setter
@Getter
public class BillRequestDTO {
    @JsonView( BillItemRequestView.Add.class )
    private Set<BillItemRequestDTO> billItems = new HashSet<>();
}
