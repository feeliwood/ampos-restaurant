package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.BillItem;

import ampos.restaurant.domain.dto.BillItemDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2019-08-09T10:23:44+0700",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"

)

@Component

public class BillItemMapperImpl implements BillItemMapper {

    @Autowired

    private BillMapper billMapper;

    @Autowired

    private MenuItemMapper menuItemMapper;

    @Override

    public List<BillItem> toEntity(List<BillItemDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<BillItem> list = new ArrayList<BillItem>();

        for ( BillItemDTO billItemDTO : dtoList ) {

            list.add( toEntity( billItemDTO ) );
        }

        return list;
    }

    @Override

    public List<BillItemDTO> toDto(List<BillItem> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<BillItemDTO> list = new ArrayList<BillItemDTO>();

        for ( BillItem billItem : entityList ) {

            list.add( toDto( billItem ) );
        }

        return list;
    }

    @Override

    public BillItemDTO toDto(BillItem billItem) {

        if ( billItem == null ) {

            return null;
        }

        BillItemDTO billItemDTO_ = new BillItemDTO();

        billItemDTO_.setId( billItem.getId() );

        billItemDTO_.setQuantity( billItem.getQuantity() );

        billItemDTO_.setMenuItem( menuItemMapper.toDto( billItem.getMenuItem() ) );

        billItemDTO_.setOrderedTime( billItem.getOrderedTime() );

        billItemDTO_.setBill( billMapper.toDto( billItem.getBill() ) );

        return billItemDTO_;
    }

    @Override

    public BillItem toEntity(BillItemDTO billItemDTO) {

        if ( billItemDTO == null ) {

            return null;
        }

        BillItem billItem_ = new BillItem();

        billItem_.setId( billItemDTO.getId() );

        billItem_.setMenuItem( menuItemMapper.toEntity( billItemDTO.getMenuItem() ) );

        billItem_.setBill( billMapper.toEntity( billItemDTO.getBill() ) );

        billItem_.setQuantity( billItemDTO.getQuantity() );

        billItem_.setOrderedTime( billItemDTO.getOrderedTime() );

        return billItem_;
    }
}

