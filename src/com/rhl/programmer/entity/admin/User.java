package com.rhl.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * 
 * @author rhl
 *
 */
@Component
public class User {
	private long id;
	private String username;
	private String password;
	private String photo;// 头像照片地址
	private int sex;// 0代表未知，1代表男，2代表女
	private Integer age;
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
