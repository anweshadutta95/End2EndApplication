package com.myproject.microservice.department.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myproject.microservice.department.repository.DepartmentRespository;
import com.myproject.microservice.department.model.Department;
import com.myproject.microservice.department.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRespository departmentRepository;
	
	@Autowired
	public DepartmentServiceImpl(DepartmentRespository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> getDepartments(String collegeName) {
		return departmentRepository.getDepartments(collegeName);
	}

	@Override
	public Department saveDepartment(Department d, String collegeName) {
		return departmentRepository.saveDepartment(d, collegeName);
	}

	@Override
	public Department getDepartment(int theId, String collegeName) {
		Department d = departmentRepository.getDepartment(theId, collegeName);
		if(d == null) {
			throw new RuntimeException("Department Id "+theId+" not found");
		}
		return d;
	}

	@Override
	public void deleteDepartment(int theId) {
		departmentRepository.deleteDepartment(theId);
	}

}
