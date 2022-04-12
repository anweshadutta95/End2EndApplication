package com.myproject.microservice.department.service;

import java.util.List;
import com.myproject.microservice.department.model.Department;

public interface DepartmentService {
	
	public List<Department> getDepartments(String collegeName);

	public Department saveDepartment(Department c, String collegeName);

	public Department getDepartment(int theId, String collegeName);

	public void deleteDepartment(int theId);

}
