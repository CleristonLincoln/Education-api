package com.education.security;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.education.model.people.Users;


public class UserSystem extends User{

	private static final long serialVersionUID = 1L;
	
	private Users users;

	
	public UserSystem(Users users, Collection<? extends GrantedAuthority> authorities) {
		super(users.getUsername(), users.getPwd(), authorities);
		this.users = users;
	}

	public Users getUsers() {
		return users;
	}

	
}
