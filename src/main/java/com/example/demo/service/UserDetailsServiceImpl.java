package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AppUser;
import com.example.demo.repository.AppUserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// hard coding the users. All passwords must be encoded.
		final Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
		
		if(!appUserOptional.isPresent()) {
			// If user not found. Throw this exception.
			System.out.println("---------------------------------User Not Found----------------------------");
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		
		AppUser appUser = appUserOptional.get();
		System.out.println("Id :: " + appUser.getId());
		System.out.println("Username :: " + appUser.getUsername());
		System.out.println("Password :: " + appUser.getPassword());
		System.out.println("Role :: " + appUser.getRole());
//		Arrays.asList(new AppUser(1, "omar", encoder.encode("12345"), "USER"),
//		new AppUser(2, "admin", encoder.encode("12345"), "ADMIN"));


		// Remember that Spring needs roles to be in this format: "ROLE_" + userRole
		// (i.e. "ROLE_ADMIN")
		// So, we need to set it to that format, so we can verify and compare roles
		// (i.e. hasRole("ADMIN")).
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());

		// The "User" class is provided by Spring and represents a model class for user
		// to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
		}
	}

//	// A (temporary) class represent the user saved in the database.
//	private static class AppUser {
//		private Integer id;
//		private String username, password;
//		private String role;
//
//		public AppUser(Integer id, String username, String password, String role) {
//			this.id = id;
//			this.username = username;
//			this.password = password;
//			this.role = role;
//		}
//
//		public Integer getId() {
//			return id;
//		}
//
//		public String getUsername() {
//			return username;
//		}
//
//		public String getPassword() {
//			return password;
//		}
//
//		public String getRole() {
//			return role;
//		}
//
//	}
