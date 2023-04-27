package com.ata.job.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ata.job.constant.Gender;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class JobJson {

	@JsonAlias("Job Title")
	@Column(name="title")
	private String title;
	
	@Column(name="salary")
	private BigDecimal salary;
	
	private BigDecimal maxSalary;
	
	@JsonAlias("Gender")
	@Column(name="gender")
	private Gender gender;
	
	@JsonAlias("Employer")
	private String employer;
	
	@JsonAlias("Timestamp")
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date timestamp;
	
	@JsonAlias("Location")
	private String location;
	
	@JsonAlias("Years at Employer")
	private String yearsAtEmployer;
	
	@JsonAlias("Years of Experience")
	private String yearsOfExperience;
	
	@JsonAlias("Signing Bonus")
	private String signingBonus;
	
	@JsonAlias("Annual Stock Value/Bonus")
	private String annualStockValue;
	
	@JsonAlias("Additional Comments")
	private String comments;
	
	@JsonAlias("Annual Bonus")
	private String annualBonus;
	
	@JsonAlias("Gender")
	private void setGender(String gender) {
		if(gender == null || gender.trim().equals(""))
			return;
		this.gender = Gender.isValid(gender) ? Gender.valueOf(gender.toUpperCase()) : null;
	};
	
	@JsonAlias("Salary")
	public void setSalary(String salary) {
		if(salary == null || salary.trim().equals(""))
			return;
		salary = salary.replaceAll(",", "").replaceAll(" ", "");
		salary = salary.startsWith("$") ? salary.substring(1) : salary;
		salary = changeKToZero(salary);
		
		if(salary.toLowerCase().endsWith("/hr") || salary.toLowerCase().endsWith("/year")) {
			salary = "0";
		}
		
		if(salary.contains("-")) {
			if(salary.trim().equals("-")) {
				salary = "0";
			}else {
				String[] temp = salary.split("-");
				salary = changeKToZero(temp[0]);
				if(temp.length > 1) {
					if(isValidInteger(temp[1].trim()))
					this.maxSalary = BigDecimal.valueOf(Long.parseLong(temp[1]));
				}
			}
		}
		if(!isValidInteger(salary))
			salary = "0";
		
		this.salary = BigDecimal.valueOf(Long.parseLong(salary));
	}
	
	public static boolean isValidInteger(String s) {
	    return s.matches("-?\\d+");
	}
	
	private String changeKToZero(String str) {
		if(str.endsWith("k") || str.endsWith("K")) {
			str = str.substring(0, str.length() -1);
			str = str +"000";
		}
		return str;
	}
	
}
