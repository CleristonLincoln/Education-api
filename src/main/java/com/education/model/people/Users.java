package com.education.model.people;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Size(min=1,max=15)
	@Column(name="username")
	private String username;
		
	private String pwd;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="users_x_user_permition",
		joinColumns = @JoinColumn(name="id_users"),
		inverseJoinColumns = @JoinColumn(name="id_user_permition"))
	private List<UserPermition> userPermition;



}
