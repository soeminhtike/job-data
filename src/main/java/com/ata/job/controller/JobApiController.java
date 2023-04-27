package com.ata.job.controller;

import java.util.List;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ata.job.entity.Job;
import com.ata.job.search.JobSearch;
import com.ata.job.service.JobService;
import com.ata.job.util.CommonUtils;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("job")
@RestController
public class JobApiController {
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping("job_data")
	public List<Job> get(HttpServletRequest request) {
		List<Job> jobList = jobService.search(JobSearch.create(request.getParameterMap()));
		return sort(jobList, request);
	}
	
	private List<Job> sort(List<Job> jobList, HttpServletRequest request) {
		String field = request.getParameter("sort");
		if(field == null || field.trim().equals(""))
			return jobList;
		
		String type = request.getParameter("sort_type");
		type = type == null || type.trim().equals("") ? "DESC" : type.trim().toUpperCase();
		
		if(field.equals("gender"))
			CommonUtils.jobSorter.sort(type, jobList, (j1,j2) -> j1.getGender().name().compareTo(j2.getGender().name()));
		else if(field.equals("job_title"))
			CommonUtils.jobSorter.sort(type, jobList, (j1,j2) -> j1.getTitle().compareTo(j2.getTitle()));
		else if(field.equals("salary"))
			CommonUtils.jobSorter.sort(type,  jobList, (j1, j2) -> j1.getSalary().compareTo(j2.getSalary()));
		
		return jobList;
	}
	
	public interface Sorter {
		void sort(String type, List<Job> jobList, BiFunction<Job, Job, Integer> sort);
	}
	
}
