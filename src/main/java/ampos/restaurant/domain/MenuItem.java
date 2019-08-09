package ampos.restaurant.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Menu.
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
    private static final String ACTIVE_COLUMN = "active";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = ID )
    private long id;

    @NotNull
    @Column( name = NAME_COLUMN, unique = true, nullable = false )
    private String name;

    @NotNull
    @Column( name = DESCRIPTION_COLUMN, nullable = false  )
    private String description;

    @Column( name = IMAGE_COLUMN )
    private String imageUrl;

    @NotNull
    @Column( name = PRICE_COLUMN, precision=10, scale=2, nullable = false)
    private BigDecimal price;

    @Column( name = DETAILS_COLUMN )
    private String details;

    @Column( name = ACTIVE_COLUMN )
    private boolean active = true;


    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails( String details ) {
        this.details = details;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

}