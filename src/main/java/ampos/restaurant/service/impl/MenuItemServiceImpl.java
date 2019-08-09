package ampos.restaurant.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import ampos.restaurant.domain.dto.MenuItemDTO;
import ampos.restaurant.domain.mapper.MenuItemMapper;
import ampos.restaurant.exception.ApplicationException;
import ampos.restaurant.domain.BillItem;
import ampos.restaurant.domain.MenuItem;
import ampos.restaurant.repository.MenuItemRepository;
import ampos.restaurant.service.MenuItemService;
import ampos.restaurant.specitifications.MenuSearchSpecifications;
import ampos.restaurant.util.RestaurantConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Menu item service implementation
 *
 */
@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {

    private final Logger log = LoggerFactory.getLogger(MenuItemServiceImpl.class);

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper menuItemMapper;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    /**
     * Save a menu item.
     *
     * @param menuItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuItemDTO save(MenuItemDTO menuItemDTO, MultipartFile file) throws ApplicationException {
        log.debug("Request to save MenuItem : {}", menuItemDTO);
        if (menuItemDTO.getImageUrl() != null )
            deleteImage( menuItemDTO.getImageUrl() );

        menuItemDTO.setImageUrl( saveImage( file ) );
        MenuItem menuItem = menuItemMapper.toEntity(menuItemDTO);
        menuItem = menuItemRepository.save(menuItem);
        return menuItemMapper.toDto(menuItem);
    }

    /**
     * Update a menu item.
     *
     * @param menuItemDTO the entity to update
     * @return the persisted entity
     */
    @Override
    public MenuItemDTO update(Long id, MenuItemDTO menuItemDTO, MultipartFile file) throws ApplicationException {
        log.debug("Request to update MenuItem : {}", menuItemDTO);
        Optional<MenuItem> menuItem = menuItemRepository.findById( id );

        // Replace existing item
        if(menuItem.isPresent()) {
            menuItemDTO.setId(menuItem.get().getId());
        }

        return this.save(menuItemDTO, file);
    }
//
//    @Override
//    public void save( ItemDTO item, MultipartFile file ) throws ApplicationException {
//        if ( item != null && menuRepository.findByTitle( item.getTitle() ) != null ) {
//            throw new ApplicationException( RestaurantConstants.MENU_ITEM_EXISTING );
//        }
//        String fileName = saveImage( file );
//        //TODO use aws , now we use local server
//        item.setImageUrl( RestaurantConstants.SPLASH + fileName );
//        item.setActive( true );
//        menuRepository.save( mapper.convert( item, MenuItem.class ) );
//    }

    @Override
    public void delete( Long id ) throws ApplicationException {
        MenuItem menuItem = menuItemRepository.findById( id ).orElseThrow( () -> new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND ) );

        String imageUrl = menuItem.getImageUrl();
        menuItemRepository.deleteById( id );
        deleteImage( getFileNameFromURL( imageUrl ) );
    }

//    @Override
//    public void editMenuItem( MenuItemDTO menuItemDTO, MultipartFile file ) throws ApplicationException {
//        //Check menu exists or not?
//        MenuItem menuItem = menuRepository.findOne( itemDTO.getId() );
//        if ( itemDTO == null || menuItem == null || !menuItem.isActive() )
//            throw new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND );
//
//        if ( file != null ) {
//            String fileName = saveImage( file );
//            itemDTO.setImageUrl( fileName );
//            deleteImage( getFileNameFromURL( menuItem.getImageUrl() ) );
//        }
//
//        menuItem.setId( menuItem.getId() );
//        menuItem.setTitle( itemDTO.getTitle() );
//        menuItem.setImageUrl( itemDTO.getImageUrl() );
//        menuItem.setPrice( itemDTO.getPrice() );
//        menuItem.setDetails( itemDTO.getDescription() );
//        menuItem.setActive( true );
//
//        menuRepository.save( menuItem );
//    }

//    @Override
//    public Page<ItemDTO> getMenuItems( Pageable pageable ) throws ApplicationException {
//        Page<MenuItem> page = menuRepository.findAll( pageable );
//        return new PageImpl<ItemDTO>( convertItemsToDTOs( page.getContent() ), pageable, page.getTotalElements() );
//
//    }

    /**
     *  Get all the clients.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuItemDTO> findAll(Pageable pageable) throws ApplicationException {
        log.debug("Request to get all MenuItems");
        return menuItemRepository.findAll(pageable)
                        .map(menuItemMapper::toDto);
    }

    /**
     *  Get one client by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MenuItemDTO findById(Long id) throws ApplicationException {
        log.debug("Request to get Client : {}", id);    // TO DO
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow( () -> new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND ) ) ;
        return menuItemMapper.toDto(menuItem);
    }
//
//    @Override
//    public ItemDTO getMenuItem( Long id ) throws ApplicationException {
//        MenuItem menuItem = menuRepository.findOne( id );
//        if ( menuItem == null || !menuItem.isActive() ) {
//            throw new ApplicationException( RestaurantConstants.MENU_ITEM_NOT_FOUND );
//        }
//        return mapper.convert( menuItem, ItemDTO.class );
//    }

//    @Override
//    public Resource loadImage( String fileName ) throws ApplicationException {
//        try {
//            Path filePath = Paths.get( RestaurantConstants.UPLOAD_DIR ).toAbsolutePath().normalize().resolve( fileName ).normalize();
//            Resource resource = new org.springframework.core.io.UrlResource( filePath.toUri() );
//            if ( resource.exists() ) {
//                return resource;
//            } else {
//                throw new ApplicationException( RestaurantConstants.FILE_NOT_FOUND );
//            }
//        } catch ( MalformedURLException ex ) {
//            throw new ApplicationException( RestaurantConstants.FILE_NOT_FOUND, ex );
//        }
//    }

    /**
     * Save image item to server
     * 
     * @param file
     * @return
     * @throws ApplicationException
     */
    private String saveImage( MultipartFile file ) throws ApplicationException {
        if ( file == null )
            return null;
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        try {
            // Copy file to the upload location (Replacing existing file with the same name)
            Path dir = Paths.get( RestaurantConstants.UPLOAD_DIR ).toAbsolutePath().normalize().resolve( fileName );
            Files.copy( file.getInputStream(), dir, StandardCopyOption.REPLACE_EXISTING );

            return fileName;
        } catch ( IOException e ) {
            throw new ApplicationException( "Could not store image" + file.getOriginalFilename(), e );
        }
    }

    /**
     * Delete image file when item is deleted
     * 
     * @param fileName
     * @throws ApplicationException
     */
    private void deleteImage( String fileName ) throws ApplicationException {
        if ( fileName == null )
            return;
        try {
            Path dir = Paths.get( RestaurantConstants.UPLOAD_DIR ).toAbsolutePath().normalize().resolve( fileName );
            Files.deleteIfExists( dir );
        } catch ( IOException e ) {
            throw new ApplicationException( "Could not delete" + fileName, e );
        }
    }

    /**
     * Get file name from a url
     * 
     * @param url
     * @return
     */
    private String getFileNameFromURL( String url ) {
        int index = url.lastIndexOf( RestaurantConstants.SPLASH );
        if ( index > 0 ) {
            return url.substring( index, url.length() );
        }
        return url;
    }
}
