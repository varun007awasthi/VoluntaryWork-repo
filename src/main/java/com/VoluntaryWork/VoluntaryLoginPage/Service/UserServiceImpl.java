package com.VoluntaryWork.VoluntaryLoginPage.Service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.VoluntaryWork.VoluntaryLoginPage.Model.RoleTO;
import com.VoluntaryWork.VoluntaryLoginPage.Model.UserTO;
import com.VoluntaryWork.VoluntaryLoginPage.Repository.UserRepository;
import com.VoluntaryWork.VoluntaryLoginPage.Repository.RoleRepository;;

@Component
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
    private RoleRepository roleRepository;
	@Override
	public void saveUser(UserTO userTO,String user_role) {
	
		userTO.setPassword(bcryptPasswordEncoder.encode(userTO.getPassword()));
		RoleTO userRole = roleRepository.findByRole(user_role);
		userTO.setRoles(new HashSet<RoleTO>(Arrays.asList(userRole)));
		userRepository.save(userTO);
		
	}

	@Override
	public UserTO findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

}
