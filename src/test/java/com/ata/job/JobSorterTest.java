package com.ata.job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ata.job.constant.Gender;
import com.ata.job.entity.Job;
import com.ata.job.util.CommonUtils;


@SpringBootTest
public class JobSorterTest {

	@Test
	public void genderAscTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithGender(Gender.FEMALE));
		jobList.add(createJobWithGender(Gender.MALE));
		jobList.add(createJobWithGender(Gender.FEMALE));
		CommonUtils.jobSorter.sort("ASC", jobList, (j1, j2) -> j1.getGender().name().compareTo(j2.getGender().name()));
		
		Assertions.assertEquals("FEMALE", jobList.get(0).getGender().name());
		Assertions.assertEquals("FEMALE", jobList.get(1).getGender().name());
		Assertions.assertEquals("MALE", jobList.get(2).getGender().name());
	}
	
	@Test
	public void genderDescTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithGender(Gender.FEMALE));
		jobList.add(createJobWithGender(Gender.MALE));
		jobList.add(createJobWithGender(Gender.FEMALE));
		CommonUtils.jobSorter.sort("DESC", jobList, (j1, j2) -> j1.getGender().name().compareTo(j2.getGender().name()));
		
		Assertions.assertEquals("MALE", jobList.get(0).getGender().name());
		Assertions.assertEquals("FEMALE", jobList.get(1).getGender().name());
		Assertions.assertEquals("FEMALE", jobList.get(2).getGender().name());
	}
	
	@Test
	public void jobTitleAscTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithJobTitle("D"));
		jobList.add(createJobWithJobTitle("B"));
		jobList.add(createJobWithJobTitle("A"));
		jobList.add(createJobWithJobTitle("C"));
		CommonUtils.jobSorter.sort("ASC", jobList, (j1, j2) -> j1.getTitle().compareTo(j2.getTitle()));
		
		Assertions.assertEquals("A", jobList.get(0).getTitle());
		Assertions.assertEquals("B", jobList.get(1).getTitle());
		Assertions.assertEquals("C", jobList.get(2).getTitle());
		Assertions.assertEquals("D", jobList.get(3).getTitle());
	}
	
	@Test
	public void jobTitleDescTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithJobTitle("D"));
		jobList.add(createJobWithJobTitle("B"));
		jobList.add(createJobWithJobTitle("A"));
		jobList.add(createJobWithJobTitle("C"));
		CommonUtils.jobSorter.sort("DESC", jobList, (j1, j2) -> j1.getTitle().compareTo(j2.getTitle()));
		
		Assertions.assertEquals("D", jobList.get(0).getTitle());
		Assertions.assertEquals("C", jobList.get(1).getTitle());
		Assertions.assertEquals("B", jobList.get(2).getTitle());
		Assertions.assertEquals("A", jobList.get(3).getTitle());
	}
	
	@Test
	public void salaryAscTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithSalary(BigDecimal.valueOf(100)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(10)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(2000)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(300)));
		CommonUtils.jobSorter.sort("ASC", jobList, (j1, j2) -> j1.getSalary().compareTo(j2.getSalary()));
		
		Assertions.assertEquals(10, jobList.get(0).getSalary().longValue());
		Assertions.assertEquals(100, jobList.get(1).getSalary().longValue());
		Assertions.assertEquals(300, jobList.get(2).getSalary().longValue());
		Assertions.assertEquals(2000, jobList.get(3).getSalary().longValue());
	}
	
	@Test
	public void salaryDescTest() {
		List<Job> jobList = new ArrayList<>();
		jobList.add(createJobWithSalary(BigDecimal.valueOf(100)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(10)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(2000)));
		jobList.add(createJobWithSalary(BigDecimal.valueOf(300)));
		CommonUtils.jobSorter.sort("DESC", jobList, (j1, j2) -> j1.getSalary().compareTo(j2.getSalary()));
		
		Assertions.assertEquals(2000, jobList.get(0).getSalary().longValue());
		Assertions.assertEquals(300, jobList.get(1).getSalary().longValue());
		Assertions.assertEquals(100, jobList.get(2).getSalary().longValue());
		Assertions.assertEquals(10, jobList.get(3).getSalary().longValue());
	}
	
	private Job createJobWithGender(Gender gender) {
		Job job = new Job();
		job.setGender(gender);
		return job;
	}
	
	private Job createJobWithJobTitle(String jobTitle) {
		Job job = new Job();
		job.setTitle(jobTitle);
		return job;
	}
	
	private Job createJobWithSalary(BigDecimal salary) {
		Job job = new Job();
		job.setSalary(salary);
		return job;
	}
}
