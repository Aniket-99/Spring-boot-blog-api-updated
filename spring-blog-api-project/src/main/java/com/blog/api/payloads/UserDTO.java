package com.blog.api.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

	private int id;

	@NotEmpty(message = "Name cannot be null")
	@Size(min = 4, message = "Username must be minimum of 4 character")
	private String name;

	@Email(message = "Invalid email address")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be minimum of 3 chars and max of 10 chars")
	private String password;

	@NotEmpty
	private String about;

	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

}
