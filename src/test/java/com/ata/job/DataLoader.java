package com.ata.job;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ata.job.entity.Job;
import com.ata.job.entity.JobJson;
import com.ata.job.service.JobService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Disabled
@SpringBootTest
public class DataLoader {
	
	@Autowired
	private JobService jobService;

	@Test
	public void load() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		File file = new File("src/test/resources/salary_survey-3.json");
		List<JobJson> jobJsonList = objectMapper.readValue(file, new TypeReference<List<JobJson>>() {});
		jobJsonList.forEach(this::save);
	}
	
	private void save(JobJson jobJson) {
		jobService.save(toJob(jobJson));
	}
	
	private Job toJob(JobJson jobJson) {
		Job job = new Job();
		job.setTitle(jobJson.getTitle());
		job.setSalary(jobJson.getSalary());
		job.setMaxSalary(jobJson.getMaxSalary());
		job.setGender(jobJson.getGender());
		job.setEmployer(jobJson.getEmployer());
		job.setTimestamp(jobJson.getTimestamp());
		job.setLocation(jobJson.getLocation());
		job.setYearsAtEmployer(jobJson.getYearsAtEmployer());
		job.setYearsOfExperience(jobJson.getYearsOfExperience());
		job.setSigningBonus(jobJson.getSigningBonus());
		job.setAnnualBonus(jobJson.getAnnualBonus());
		job.setAnnualStockValue(jobJson.getAnnualStockValue());
		job.setComments(jobJson.getComments());
		job.setAnnualBonus(jobJson.getAnnualBonus());
		return job;
	}
}
