package com.education.model.adress;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Street.class)
public abstract class Street_ {

	public static volatile SingularAttribute<Street, String> name;
	public static volatile SingularAttribute<Street, TypeAdress> typeAdress;
	public static volatile SingularAttribute<Street, Long> id;
	public static volatile SingularAttribute<Street, Neighborhood> neighborhood;
	public static volatile SingularAttribute<Street, String> complement;
	public static volatile SingularAttribute<Street, String> cep;

}

