package com.education.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.education.model.people.Users;
import com.education.repository.UsersRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> usersOptional = repository.findByUsername(username);
	
		Users users = usersOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		
		return new UserSystem(users, getPermissoes(users));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Users users) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		users.getUserPermition().forEach(
				p -> authorities.add(
						new SimpleGrantedAuthority(p.getDescrition())
						)
				);
		
		return authorities;
	}

}

