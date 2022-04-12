package com.myproject.microservice.college.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myproject.microservice.college.repository.impl.CollegeRespositoryImpl;
import com.myproject.microservice.college.model.College;
import com.myproject.microservice.college.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService{

	@Autowired
	private CollegeRespositoryImpl collegeDAO;
	
	@Override
	public List<College> getColleges() {
		return collegeDAO.getColleges();
	}

	@Override
	public College saveCollege(College c) {
		return collegeDAO.saveCollege(c);
	}

	@Override
	public College getCollegeById(int theId) {
		College c = collegeDAO.getCollegeById(theId);
		if(c == null) {
			throw new RuntimeException("College Id "+theId+" not found");
		}
		return c;
	}
	
	@Override
	public College getCollegeByName(String name) {
		College c = collegeDAO.getCollegeByName(name);
		return c;
	}
	
	@Override
	public College updateCollege(String oldName, String newName) {
		College c = collegeDAO.updateCollege(oldName, newName);
		return c;
	}

	@Override
	public void deleteCollege(int theId) {
		collegeDAO.deleteCollege(theId);
	}

}
