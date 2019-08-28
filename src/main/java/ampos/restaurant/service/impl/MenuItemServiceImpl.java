package ampos.restaurant.service.impl;

import java.util.Arrays;

import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.domain.dto.MenuItemRequestDTO;
import ampos.restaurant.domain.mapper.MenuItemMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.repository.MenuItemRepository;
import ampos.restaurant.service.MenuItemService;
import ampos.restaurant.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Menu item service implementation
 *
 */
@Service
public class MenuItemServiceImpl extends GenericServiceImpl<MenuItemRequestDTO, MenuItemDTO, Long, MenuItem, MenuItemRepository, MenuItemMapper> implements MenuItemService {

    MenuItemServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        super(menuItemRepository, menuItemMapper);
    }

    @Override
    MenuItem mergeExistingAndNewEntity(MenuItem existingMenuItem, MenuItem newMenuItem) {
        // Set existing id and replace the whole new MenuItem to existing MenuItem
        newMenuItem.setId( existingMenuItem.getId() );
        return newMenuItem;
    }

    @Override
    public void delete( Long id ) {
        repository.deleteAllMenuItemWithIds( Arrays.asList(id) );
    }

    @Override
    @Transactional( readOnly = true )
    public Page<MenuItemDTO> search( String keyword, Pageable pageable ) {
        return repository.search( keyword.toLowerCase(), pageable ).map( this.mapper::entityToDto );
    }
}
