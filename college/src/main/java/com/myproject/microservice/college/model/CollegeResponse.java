package com.myproject.microservice.college.model;

import java.util.List;

public class CollegeResponse {

	private List<College> colleges;

	public List<College> getColleges() {
		return colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}
	
}
