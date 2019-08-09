package ampos.restaurant.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MenuItem.class)
public abstract class MenuItem_ {

	public static volatile SingularAttribute<MenuItem, BigDecimal> price;
	public static volatile SingularAttribute<MenuItem, String> imageUrl;
	public static volatile SingularAttribute<MenuItem, String> name;
	public static volatile SingularAttribute<MenuItem, String> description;
	public static volatile SingularAttribute<MenuItem, Boolean> active;
	public static volatile SingularAttribute<MenuItem, String> details;
	public static volatile SingularAttribute<MenuItem, Long> id;

}

