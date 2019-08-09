package ampos.restaurant.domain.mapper;

import ampos.restaurant.domain.MenuItem;

import ampos.restaurant.domain.dto.MenuItemDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2019-08-09T10:23:44+0700",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"

)

@Component

public class MenuItemMapperImpl implements MenuItemMapper {

    @Override

    public List<MenuItem> toEntity(List<MenuItemDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<MenuItem> list = new ArrayList<MenuItem>();

        for ( MenuItemDTO menuItemDTO : dtoList ) {

            list.add( toEntity( menuItemDTO ) );
        }

        return list;
    }

    @Override

    public List<MenuItemDTO> toDto(List<MenuItem> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<MenuItemDTO> list = new ArrayList<MenuItemDTO>();

        for ( MenuItem menuItem : entityList ) {

            list.add( toDto( menuItem ) );
        }

        return list;
    }

    @Override

    public MenuItemDTO toDto(MenuItem menuItem) {

        if ( menuItem == null ) {

            return null;
        }

        MenuItemDTO menuItemDTO_ = new MenuItemDTO();

        menuItemDTO_.setId( menuItem.getId() );

        menuItemDTO_.setName( menuItem.getName() );

        menuItemDTO_.setDescription( menuItem.getDescription() );

        menuItemDTO_.setImageUrl( menuItem.getImageUrl() );

        menuItemDTO_.setPrice( menuItem.getPrice() );

        menuItemDTO_.setDetails( menuItem.getDetails() );

        return menuItemDTO_;
    }

    @Override

    public MenuItem toEntity(MenuItemDTO menuItemDTO) {

        if ( menuItemDTO == null ) {

            return null;
        }

        MenuItem menuItem_ = new MenuItem();

        if ( menuItemDTO.getId() != null ) {

            menuItem_.setId( menuItemDTO.getId() );
        }

        menuItem_.setName( menuItemDTO.getName() );

        menuItem_.setDescription( menuItemDTO.getDescription() );

        menuItem_.setImageUrl( menuItemDTO.getImageUrl() );

        menuItem_.setPrice( menuItemDTO.getPrice() );

        menuItem_.setDetails( menuItemDTO.getDetails() );

        return menuItem_;
    }
}

