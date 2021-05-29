package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is alresady ther !!");
			throw new Exception("User alresdy present !!");
		}
		else {
			// user create
			for(UserRole ur:userRole) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRole().addAll(userRole);
			local = this.userRepository.save(user);
		}
		return local;
	}

}
