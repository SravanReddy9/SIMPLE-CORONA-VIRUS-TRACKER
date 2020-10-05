package com.covidcases.project.model;

public class DataModel {
	
	private String country;
	private int total_cases;
	private int cases_today;
	//private int recovories;
	
	
	public DataModel() {}
	
	
	public DataModel(String country, int total_cases, int cases_today) {
		super();
		this.country = country;
		this.total_cases = total_cases;
		this.cases_today = cases_today;
	} 


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getTotal_cases() {
		return total_cases;
	}


	public void setTotal_cases(int total_cases) {
		this.total_cases = total_cases;
	}


	public int getCases_today() {
		return cases_today;
	}


	public void setCases_today(int cases_today) {
		this.cases_today = cases_today;
	}


	@Override
	public String toString() {
		return "DataModel [country=" + country + ", total_cases=" + total_cases + ", cases_today=" + cases_today + "]";
	}


	
	
	
	
	 

}
