package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * MenuItem entity
 */
@Entity
@Table( name = MenuItem.TABLE_NAME )
public class MenuItem implements Serializable {
    protected static final String TABLE_NAME = "menu_item";
    private static final String ID = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String IMAGE_COLUMN = "image_url";
    private static final String PRICE_COLUMN = "price";
    private static final String DETAILS_COLUMN = "details";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @NotNull
    @Column( name = NAME_COLUMN, unique = true, nullable = false )
    private String name;

    @NotNull
    @Column( name = DESCRIPTION_COLUMN, nullable = false )
    private String description;

    @Column( name = IMAGE_COLUMN )
    private String imageUrl;

    @NotNull
    @Column( name = PRICE_COLUMN, precision = 10, scale = 2, nullable = false )
    private BigDecimal price;

    @Column( name = DETAILS_COLUMN )
    private String details;

    /**
     *
     * Default constructor
     */
    public MenuItem() {
        super();
    }

    /**
     *
     * @param id
     * @param name
     * @param description
     * @param imageUrl
     * @param price
     * @param details
     */
    public MenuItem( long id, @NotNull String name, @NotNull String description, String imageUrl, @NotNull BigDecimal price, String details ) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.details = details;
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     *            to be set
     */
    public void setId( long id ) {
        this.id = id;
    }

    /**
     *
     * @return MenuItem name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            set MenuItem name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     *
     * @return MenuItem description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            set MenuItem description
     */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
     *
     * @return url to MenuItem image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     *
     * @param imageUrl
     *            set url to MenuItem image
     */
    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return MenuItem price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param price
     *            set MenuItem price
     */
    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    /**
     *
     * @return MenuItem details
     */
    public String getDetails() {
        return details;
    }

    /**
     *
     * @param details
     *            set MenuItem details
     */
    public void setDetails( String details ) {
        this.details = details;
    }
}