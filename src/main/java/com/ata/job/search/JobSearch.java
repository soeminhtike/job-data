package com.ata.job.search;

import java.util.Map;

import com.ata.job.constant.Gender;

import lombok.Data;

@Data
public class JobSearch implements EntitySearch {

	private StringBuffer filter= new StringBuffer();
	
	private boolean empty = true;

	@Override
	public String createQuery() {
		return filter.length() == 0 ? "select j from Job j" : "select j from Job j where " + filter.substring(5);
	}

	public static JobSearch create(Map<String, String[]> param) {
		JobSearch jobSearch = new JobSearch();
		jobSearch.resolveParam(param);
		return jobSearch;
	}

	public void resolveParam(Map<String, String[]> request) {
		if(request== null || request.size() == 0) {
			empty = true;
			return;
		}
		empty = false;
		if(request.containsKey("fields"))
			resolveParamWithSparseAttribute(request);
		else
			resolveParamWithAttribute(request);
		
	}
	
	private void resolveParamWithSparseAttribute(Map<String, String[]> request) {
		String[] fields = request.get("fields")[0].split(",");
		String[] values = request.get("values")[0].split(",");
		if(fields.length != values.length) 
			return;
		
		String salary = null;
		String salaryGte = null;
		String salaryLt = null;
		
		for(int i=0;i<fields.length;i++) {
			String key = fields[i];
			
			if(key.equals("gender")) {
				String query = extractGender(values[i]);
				if(query != null)
					filter.append(" AND " + query);
			}else if(key.equals("job_title")) {
				String query =extractJobTitle(values[i]);
				if(query != null)
					filter.append(" AND " + query);
			}else if(key.equals("salary")) {
				salary = values[i];
			}else if(key.equals("salary[gte]")) {
				salaryGte = values[i];
			}else if(key.equals("salary[lt]")) {
				salaryLt = values[i];
			}
		}
		
		String query = extractSalary(salary, salaryGte, salaryLt);
		if(query != null)
			filter.append(" AND " + query);
	}
	
	private void resolveParamWithAttribute(Map<String, String[]> request) {
		String query = extractGender(request.get("gender"));
		if(query != null)
			filter.append(" AND " + query);
		
		query = extractJobTitle(request.get("jobTitle"));
		if(query != null)
			filter.append(" AND " + query);
		
		query = extractSalary(request.get("salary"), request.get("salary[gte]"), request.get(("salary[lt]")));
		if(query != null)
			filter.append(" AND " + query);
	}
	
	public String extractSalary(String salary, String salaryGte, String salaryLt) {
		if(salary != null)
			return "(j.salary="+salary+")";
		
		if(salaryGte == null && salaryLt == null) {
			return null;
		}
		
		if(salaryGte != null && salaryLt != null)
			return "(j.salary>"+salaryGte +" AND j.salary<"+salaryLt+")";
		
		if(salaryGte != null)
			return "(j.salary>"+salaryGte+")";
		
		return "(j.salary<"+salaryLt+")";
	}
	
	public String extractSalary(String[] pSalary, String[] pSalaryGte, String[] pSalaryLt) {
		return extractSalary(getValue(pSalary), getValue(pSalaryGte), getValue(pSalaryLt));
	}
	
	public String extractJobTitle(String[] str) {
		String jobTitle = getValue(str);
		return extractJobTitle(jobTitle);
	}
	
	public String extractJobTitle(String jobTitle) {
		if (jobTitle == null) 
			return null;
		
		jobTitle = jobTitle.trim().toLowerCase();
		return "( j.title LIKE '%"+jobTitle+"%' )";
	}
	
	public String extractGender(String gender) {
		if (gender == null)
			return null;
		
		StringBuffer query = new StringBuffer("(");

		String[] genders = gender.trim().toUpperCase().split(",");
		for (String tGender : genders) {
			if(!Gender.isValid(tGender))
				continue;
			query.append(String.format(" j.gender='%s' OR", tGender));
		}
		return query.substring(0, query.length() - 2)+")";
	}

	public String extractGender(String[] str) {
		return extractGender(getValue(str));
		
	}

	private String getValue(String[] str) {
		if( str == null || str.length == 0 )
			return null;
		/*
		String value = str[0];
		if(value.startsWith("'") || value.startsWith("\"")) {
			return value.substring(1, value.length() -2);
		}else  {
			return value;
		} */
		return str[0];
	}

}
