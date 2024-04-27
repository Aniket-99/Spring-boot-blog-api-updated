package com.blog.api.services;

import java.util.List;

import com.blog.api.payloads.UserDTO;

public interface UserService {
	
	UserDTO registerNewUser(UserDTO userDTO);

	UserDTO createUser(UserDTO user);

	UserDTO updateUser(UserDTO userDTO, Integer userId);

	UserDTO getUserById(Integer id);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer userId);
}
