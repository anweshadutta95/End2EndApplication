package com.myproject.microservice.faculty.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.myproject.microservice.faculty.model.College;

@FeignClient(name="college-service")
public interface CollegeProxy {
	
	@GetMapping("/colleges/name/{collegeName}")
	public College getCollegeByName(@PathVariable String collegeName);

}
