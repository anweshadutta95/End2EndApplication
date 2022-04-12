package com.myproject.microservice.college.service;

import java.util.List;
import com.myproject.microservice.college.model.College;

public interface CollegeService {
	
	public List<College> getColleges();

	public College saveCollege(College c);

	public College getCollegeById(int theId);
	
	public College getCollegeByName(String name);
	
	public College updateCollege(String oldName, String newName);

	public void deleteCollege(int theId);

}
