package ampos.restaurant.service;

import ampos.restaurant.domain.dto.MenuItemRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.exception.ApplicationException;

/**
 * Menu Item Service
 */
public interface MenuItemService extends GenericService<MenuItemRequestDTO, MenuItemDTO, Long> {

    /**
     * Search menu items by keyword
     *
     * @param keyword
     *            : the keyword of menu title or description or additional
     *            details.
     * @param pageable
     *            : the pagination information
     * @return
     * @throws ApplicationException
     */
    Page<MenuItemDTO> search( String keyword, Pageable pageable ) throws ApplicationException;
}
