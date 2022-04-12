package com.myproject.microservice.department.repository;

import java.util.List;
import com.myproject.microservice.department.model.Department;

public interface DepartmentRespository {

	public List<Department> getDepartments(String collegeName);

	public Department saveDepartment(Department c, String collegeName);

	public Department getDepartment(int theId, String collegeName);

	public void deleteDepartment(int theId);
	
}