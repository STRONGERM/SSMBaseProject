package com.rhl.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.rhl.programmer.entity.admin.User;


/**
 * user�û�service
 * @author rhl
 *
 */

@Service
public interface UserService {
	public User findByUsername(String username);
}
