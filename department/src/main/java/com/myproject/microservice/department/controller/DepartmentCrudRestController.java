package com.myproject.microservice.department.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myproject.microservice.department.model.Department;
import com.myproject.microservice.department.exception.DepartmentNotFoundException;
import com.myproject.microservice.department.service.DepartmentService;

@RestController
public class DepartmentCrudRestController {
	
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/departments/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public List<Department> getAllDepartments(@PathVariable String collegeName) {
		return departmentService.getDepartments(collegeName);
	}
	
	@GetMapping("/departments/{collegeName}/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Department getDepartmentById(@PathVariable String collegeName, @PathVariable int departmentId) {
		Department d = departmentService.getDepartment(departmentId, collegeName);
		if(d == null ) {
			throw new DepartmentNotFoundException("Department ID "+departmentId+" not found");
		}
		return d;
	}
	
	@PostMapping("/departments/{collegeName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) 
	public Department saveDepartment(@RequestBody Department department, @PathVariable String collegeName) {
		department.setId(0);
		return departmentService.saveDepartment(department, collegeName);
	}
	
	/*@PutMapping("/departments")
	public Department updateDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}
	
	@DeleteMapping("/departments/{departmentId}")
	public String deleteDepartmentById(@PathVariable int departmentId) {
		Department c = departmentService.getDepartment(departmentId);
		if(c == null ) {
			throw new DepartmentNotFoundException("Department ID "+departmentId+" not found");
		}
		departmentService.deleteDepartment(departmentId);
		return "Department ID "+departmentId+" successfully deleted";
	}*/
}
