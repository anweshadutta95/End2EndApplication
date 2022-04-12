package com.myproject.microservice.faculty.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myproject.microservice.faculty.repository.FacultyRespository;
import com.myproject.microservice.faculty.model.Faculty;
import com.myproject.microservice.faculty.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService{

	private FacultyRespository facultyRespository;
	
	@Autowired
	public FacultyServiceImpl(FacultyRespository facultyRespository) {
		this.facultyRespository = facultyRespository;
	}

	@Override
	public List<Faculty> getFaculties(String collegeName) {
		return facultyRespository.getFaculties(collegeName);
	}

	@Override
	public Faculty saveFaculty(Faculty f, int collegeId) {
		return facultyRespository.saveFaculty(f, collegeId);
	}
	
	@Override
	public Faculty assignFaculty(String facultyName, int departmentId) {
		return facultyRespository.assignFaculty(facultyName, departmentId);
	}
	
	@Override
	public Faculty assignHod(String facultyName, int departmentId) {
		return facultyRespository.assignHod(facultyName, departmentId);
	}
	
	@Override
	public Faculty getHod(int departmentId) {
		return facultyRespository.getHod(departmentId);
	}

	@Override
	public Faculty getFaculty(int theId) {
		Faculty f = facultyRespository.getFaculty(theId);
		if(f == null) {
			throw new RuntimeException("Faculty Id "+theId+" not found");
		}
		return f;
	}
	
	@Override
	public void deleteFaculty(int theId) {
		facultyRespository.deleteFaculty(theId);
	}

	@Override
	public List<Faculty> getFacultyByDepartment(String collegeName, String departmentName) {
		return facultyRespository.getFacultyByDepartment(collegeName, departmentName);
	}

}
