package com.VoluntaryWork.VoluntaryLoginPage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.VoluntaryWork.VoluntaryLoginPage.Model.RoleTO;;;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleTO, Integer>{
	RoleTO findByRole(String role);

}