package com.myproject.microservice.department.repository.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.myproject.microservice.department.repository.DepartmentRespository;
import com.myproject.microservice.department.model.College;
import com.myproject.microservice.department.model.Department;
import com.myproject.microservice.department.proxy.CollegeProxy;

@Repository
public class DepartmentRespositoryImpl implements DepartmentRespository{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CollegeProxy collegeProxy;
	
	@Override
	public List<Department> getDepartments(String collegeName) {
		
		//IMPLEMENT
		College c = collegeProxy.getCollegeByName(collegeName);
		if(c == null) {
			throw new RuntimeException("College Name "+collegeName+" not found");
		}
		System.out.println("Colleges retrieved = "+c.toString());
		//create the query
		
		Set<Department> departments = c.getDepartments();
		
		//return the list of departments;
		return departments.stream().collect(Collectors.toList());
	}

	@Override
	public Department saveDepartment(Department d, String collegeName) {
		
		Session s = sessionFactory.openSession();
		//begin transaction
		s.beginTransaction();
		//get the college
		College c = collegeProxy.getCollegeByName(collegeName);
		if(c == null) {
			throw new RuntimeException("College Name "+collegeName+" not found");
		}
		System.out.println("College retrieved = "+c.toString());
		//System.out.println("College retrieved existing depts = "+c.getDepartments().get(0).toString());
		//add the i/p dept to retrieved college
		//d.setId(0);
		for (Department dp : c.getDepartments()) {
			System.out.println("Department = "+dp.toString());
		}
		System.out.println("Dept is = "+d.toString());
		c.add(d);
		//System.out.println("College retrieved existing depts after add = "+c.getDepartments().get(0).toString()+" :: "
		//+c.getDepartments().get(1).toString());
		//get session factory
		//SessionFactory sessionFactory = getSessionFactory();
		//IMPLEMENT
		
		//save the department
		s.saveOrUpdate(c);
		s.getTransaction().commit();
		s.close();
		return d;
	}

	@Override
	public Department getDepartment(int theId, String collegeName) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.getCurrentSession();
		//IMPLEMENT
		Department d = (Department) s.get(Department.class, theId);
		return d;
	}

	@Override
	public void deleteDepartment(int theId) {
		
		//SessionFactory sessionFactory = getSessionFactory();
		Session s = sessionFactory.getCurrentSession();
		//OPTION 1: get the department and delete - 2 db operations
		//Department c = (Department) s.get(Department.class, theId);
		//s.delete(c);
		//OPTION 2: HQL - 1 db operation
		
		//IMPLEMENT
		@SuppressWarnings("unchecked")
		Query<Department> q = s.createQuery("delete from Department where id:=departmentId");
		q.setParameter("departmentId", theId);
		q.executeUpdate();
	}

}
