package ampos.restaurant.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillItem.class)
public abstract class BillItem_ {

	public static volatile SingularAttribute<BillItem, ZonedDateTime> orderedTime;
	public static volatile SingularAttribute<BillItem, Integer> quantity;
	public static volatile SingularAttribute<BillItem, Bill> bill;
	public static volatile SingularAttribute<BillItem, Long> id;
	public static volatile SingularAttribute<BillItem, MenuItem> menuItem;

}

