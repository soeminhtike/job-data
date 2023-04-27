package com.ata.job;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ata.job.search.JobSearch;

@SpringBootTest
public class JobSearchTest {

	@Test
	public void genderExtractTest() {
		JobSearch jwa = JobSearch.create(null);
		
		String[] param1 = {};
		String result = jwa.extractGender(param1);
		Assertions.assertNull(result);

		String[] param2 ={"MALE"};
		result = jwa.extractGender(param2);
		Assertions.assertEquals("( j.gender='MALE' )", result);
		
		String[] param3 = {"MALE,FEMALE"};
		result = jwa.extractGender(param3);
		Assertions.assertEquals("( j.gender='MALE' OR j.gender='FEMALE' )", result);
		
		String[] param4 ={"MALE,OTHER"};
		result = jwa.extractGender(param4);
		Assertions.assertEquals("( j.gender='MALE' )", result);
	}
	
	@Test
	public void jobTitleExtractTest() {
		JobSearch jwa = JobSearch.create(null);
		
		String[] param1 = {};
		String result = jwa.extractJobTitle(param1);
		Assertions.assertNull(result);
		
		String[] param2 = {"job1" };
		result = jwa.extractJobTitle(param2);
		Assertions.assertEquals("( j.title LIKE '%job1%' )", result);
		
		String[] param3 = {"Developer" };
		result = jwa.extractJobTitle(param3);
		Assertions.assertEquals("( j.title LIKE '%developer%' )", result);
	}
	
	@Test
	public void salaryExtractTest() {
		JobSearch jwa = JobSearch.create(null);
		String[] param1 = {};
		String result = jwa.extractSalary(param1, param1, param1);
		Assertions.assertNull(result);
		
		String[] salary1 = {"10000"};
		String[] salaryGte1 = {};
		String[] salaryLt1 = {};
		result = jwa.extractSalary(salary1, salaryGte1, salaryLt1);
		Assertions.assertEquals("(j.salary=10000)", result);
		
		String[] salary2 = {};
		String[] salaryGte2 = {"1000"};
		String[] salaryLt2 = {"5000"};
		result = jwa.extractSalary(salary2, salaryGte2, salaryLt2);
		Assertions.assertEquals("(j.salary>1000 AND j.salary<5000)", result);
		
		String[] salary3 = {};
		String[] salaryGte3 = {"5000"};
		String[] salaryLt3 = {};
		result = jwa.extractSalary(salary3, salaryGte3, salaryLt3);
		Assertions.assertEquals("(j.salary>5000)", result);
		
		String[] salary4 = {};
		String[] salaryGte4 = {};
		String[] salaryLt4 = {"1000"};
		result = jwa.extractSalary(salary4, salaryGte4, salaryLt4);
		Assertions.assertEquals("(j.salary<1000)", result);
	}
	
	@Test
	public void fullQueryGenerateTest() {
		Map<String, String[]> map = new HashMap<>();
		String query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j", query);
		
		map = new HashMap<>();
		String[] param1 = {"MALe"};
		
		map.put("gender", param1);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where ( j.gender='MALE' )", query);
		//-------------
		String[] param2 = {"Developer"};
		map.put("jobTitle", param2);
		
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where ( j.gender='MALE' ) AND ( j.title LIKE '%developer%' )", query);
		//---------------
		map = new HashMap<>();
		map.put("jobTitle", param2);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where ( j.title LIKE '%developer%' )", query);
		//-----------------
		map = new HashMap<>();
		String[] param3 = {"10000"};
		map.put("salary", param3);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where (j.salary=10000)", query);
		//---------
		map = new HashMap<>();
		String[] param4 = {"10000"};
		map.put("salary[gte]", param4);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where (j.salary>10000)", query);
	}
	
	@Test
	public void spareAttributeTest() {
		String[] fields1 = {"gender"};
		String[] value1 = {"MALE"};
		
		Map<String, String[]> map = new HashMap<>();
		map.put("fields", fields1);
		map.put("values", value1);
		String query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where ( j.gender='MALE' )", query);
		
		String[] value2 = { "'MALE,FEMALE'" }; // multiple values doesn't support with spare attribute
		map.put("values", value2);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j", query);
		
		String[] fields2 = {"job_title"};
		map.put("fields", fields2);
		String[] values2 = {"Developer"};
		map.put("values", values2);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where ( j.title LIKE '%developer%' )", query);
		
		String[] fields3 = {"salary"};
		map.put("fields", fields3);
		String[] values3 = {"3000" };
		map.put("values", values3);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where (j.salary=3000)", query);	
		String[] fields4 = {"salary[gte],salary[lt]" };
		map.put("fields", fields4);
		String[] values4 = {"4000,7000"};
		map.put("values", values4);
		query = JobSearch.create(map).createQuery();
		Assertions.assertEquals("select j from Job j where (j.salary>4000 AND j.salary<7000)", query);
		
	}
}
