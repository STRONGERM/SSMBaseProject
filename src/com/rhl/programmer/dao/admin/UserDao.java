package com.rhl.programmer.dao.admin;

import org.springframework.stereotype.Repository;

import com.rhl.programmer.entity.admin.User;

/**
 * user”√ªßdao
 * @author rhl
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);
}
