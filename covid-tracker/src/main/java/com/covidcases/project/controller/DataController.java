package com.covidcases.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.covidcases.project.model.DataModel;
import com.covidcases.project.services.DataService;

@Controller
public class DataController {
	@Autowired
	DataService dataService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<DataModel> stats = dataService.getStats();
		int cases_today = stats.stream().mapToInt(stat->stat.getCases_today()).sum();
		int total_no_of_cases = stats.stream().mapToInt(stat->stat.getCases_today()).sum();
		
		model.addAttribute("datamodel", dataService.getStats());
		
		/*
		 * model.addAttribute("location", stats); model.addAttribute("total_cases",
		 * total_cases); model.addAttribute("cases_today", cases_today);
		 */
		
		model.addAttribute("total_no_of_cases",total_no_of_cases );
		
		return "home";
	}
	
	
	
}
