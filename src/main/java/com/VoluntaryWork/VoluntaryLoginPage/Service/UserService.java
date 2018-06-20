package com.VoluntaryWork.VoluntaryLoginPage.Service;

import com.VoluntaryWork.VoluntaryLoginPage.Model.UserTO;

public interface UserService {
	public UserTO findUserByEmail(String email);
	public void saveUser(UserTO user,String user_role);
}
