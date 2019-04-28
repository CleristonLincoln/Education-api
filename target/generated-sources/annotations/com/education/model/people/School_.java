package com.education.model.people;

import com.education.model.adress.Street;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(School.class)
public abstract class School_ {

	public static volatile SingularAttribute<School, String> numberHome;
	public static volatile ListAttribute<School, Teacher> teacher;
	public static volatile SingularAttribute<School, String> im;
	public static volatile SingularAttribute<School, String> complementHome;
	public static volatile SingularAttribute<School, Street> street;
	public static volatile SingularAttribute<School, String> nameSchool;
	public static volatile SingularAttribute<School, String> nameSocial;
	public static volatile SingularAttribute<School, Long> id;
	public static volatile SingularAttribute<School, String> cnpj;
	public static volatile SingularAttribute<School, String> ie;

}

