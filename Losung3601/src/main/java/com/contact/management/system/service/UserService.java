package com.contact.management.system.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.contact.management.system.entity.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}
