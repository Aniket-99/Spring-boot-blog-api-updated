package com.blog.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.api.config.AppConstants;
import com.blog.api.entities.Role;
import com.blog.api.entities.User;
import com.blog.api.exceptions.ResourceNotFoundException;
import com.blog.api.payloads.UserDTO;
import com.blog.api.repositories.RoleRepo;
import com.blog.api.repositories.UserRepo;
import com.blog.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User savedUser = userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());

		User updatedUser = this.userRepo.save(user);
		return this.userToDTO(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		return users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	private User dtoToUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDTO userToDTO(User user) {
		UserDTO userDto = this.modelMapper.map(user, UserDTO.class);

		return userDto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
		user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);

		User savedUser = this.userRepo.save(user);

		return this.modelMapper.map(savedUser, UserDTO.class);
	}

}
