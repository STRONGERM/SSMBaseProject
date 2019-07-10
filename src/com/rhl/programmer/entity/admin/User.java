package com.rhl.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * �û�ʵ����
 * 
 * @author rhl
 *
 */
@Component
public class User {
	private long id;
	private String username;
	private String password;
	private String photo;// ͷ����Ƭ��ַ
	private int sex;// 0����δ֪��1�����У�2����Ů
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
