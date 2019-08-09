package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.Bill;

import ampos.restaurant.domain.BillItem;

import ampos.restaurant.domain.dto.BillDTO;

import ampos.restaurant.domain.dto.BillItemDTO;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;

import java.util.Set;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2019-08-09T10:23:44+0700",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"

)

@Component

public class BillMapperImpl implements BillMapper {

    @Autowired

    private BillItemMapper billItemMapper;

    @Override

    public List<Bill> toEntity(List<BillDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Bill> list = new ArrayList<Bill>();

        for ( BillDTO billDTO : dtoList ) {

            list.add( toEntity( billDTO ) );
        }

        return list;
    }

    @Override

    public List<BillDTO> toDto(List<Bill> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<BillDTO> list = new ArrayList<BillDTO>();

        for ( Bill bill : entityList ) {

            list.add( toDto( bill ) );
        }

        return list;
    }

    @Override

    public BillDTO toDto(Bill bill) {

        if ( bill == null ) {

            return null;
        }

        BillDTO billDTO_ = new BillDTO();

        billDTO_.setId( bill.getId() );

        Set<BillItemDTO> set = billItemSetToBillItemDTOSet( bill.getBillItems() );

        if ( set != null ) {

            billDTO_.setBillItems( set );
        }

        return billDTO_;
    }

    @Override

    public Bill toEntity(BillDTO billDTO) {

        if ( billDTO == null ) {

            return null;
        }

        Bill bill_ = new Bill();

        bill_.setId( billDTO.getId() );

        Set<BillItem> set = billItemDTOSetToBillItemSet( billDTO.getBillItems() );

        if ( set != null ) {

            bill_.setBillItems( set );
        }

        return bill_;
    }

    protected Set<BillItemDTO> billItemSetToBillItemDTOSet(Set<BillItem> set) {

        if ( set == null ) {

            return null;
        }

        Set<BillItemDTO> set_ = new HashSet<BillItemDTO>();

        for ( BillItem billItem : set ) {

            set_.add( billItemMapper.toDto( billItem ) );
        }

        return set_;
    }

    protected Set<BillItem> billItemDTOSetToBillItemSet(Set<BillItemDTO> set) {

        if ( set == null ) {

            return null;
        }

        Set<BillItem> set_ = new HashSet<BillItem>();

        for ( BillItemDTO billItemDTO : set ) {

            set_.add( billItemMapper.toEntity( billItemDTO ) );
        }

        return set_;
    }
}

