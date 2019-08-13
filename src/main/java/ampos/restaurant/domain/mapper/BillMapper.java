package ampos.restaurant.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ampos.restaurant.domain.Bill;
import ampos.restaurant.domain.dto.BillDTO;

/**
 * Mapper for the entity Bill and its DTO BillDTO.
 */
@Mapper( componentModel = "spring", uses = { BillItemMapper.class } )
public interface BillMapper extends EntityMapper<BillDTO, Bill> {

    /**
     *
     * @param bill entity
     * @return billDTO which is DTO of bill entity
     */
    @Mapping( expression = "java(bill.getTotal())", target = "total" )
    BillDTO toDto( Bill bill );

    /**
     *
     * @param billDTO
     * @return bill which is entity of billDTO
     */
    Bill toEntity( BillDTO billDTO );

    /**
     * Create a dummy Bill object from id to prevent infinite loop in bidirectional relationship when mapping
     * @param id
     * @return
     */
    default Bill fromId( Long id ) {
        if ( id == null ) {
            return null;
        }
        Bill Bill = new Bill();
        Bill.setId( id );
        return Bill;
    }
}
