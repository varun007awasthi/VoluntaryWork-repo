package com.VoluntaryWork.VoluntaryLoginPage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VoluntaryWork.VoluntaryLoginPage.Model.UserTO;

@Repository("userTORepository")
public interface UserRepository extends JpaRepository<UserTO, Long> {
	
	 UserTO findUserByEmail(String email);
}
