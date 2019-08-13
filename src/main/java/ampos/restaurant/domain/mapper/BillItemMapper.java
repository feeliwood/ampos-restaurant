package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.dto.BillItemDTO;
import ampos.restaurant.repository.MenuItemRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = { BillMapper.class, MenuItemMapper.class, MenuItemRepository.class } )
public interface BillItemMapper extends EntityMapper<BillItemDTO, BillItem> {

    /**
     *
     * @param billItem
     * @return billItemDTO which is DTO of billItem
     */
    @Mapping( source = "bill.id", target = "billId" )
    @Mapping( expression = "java(billItem.getSubTotal())", target = "subTotal" )
    BillItemDTO toDto( BillItem billItem );

    /**
     *
     * @param billItemDTO
     * @return billItem which is entity of billItemDTO
     */
    @Mapping( source = "billId", target = "bill" )
    BillItem toEntity( BillItemDTO billItemDTO );

    /**
     * Create dummy billItem object from id to prevent infinite loop in bidirectional relationship when mapping
     * @param id
     * @return
     */
    default BillItem fromId( Long id ) {
        if ( id == null ) {
            return null;
        }
        BillItem Bill = new BillItem();
        Bill.setId( id );
        return Bill;
    }
}