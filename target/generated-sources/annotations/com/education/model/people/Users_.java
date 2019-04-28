package com.education.model.people;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Users.class)
public abstract class Users_ {

	public static volatile SingularAttribute<Users, Long> id;
	public static volatile SingularAttribute<Users, String> pwd;
	public static volatile ListAttribute<Users, UserPermition> userPermition;
	public static volatile SingularAttribute<Users, String> username;

}

