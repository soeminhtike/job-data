package com.ata.job.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.ata.job.constant.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name="job")
@Entity
@EqualsAndHashCode(callSuper = true)
public class Job extends AbstractEntity{

	@Column(name="title")
	private String title;
	
	@Column(name="salary")
	private BigDecimal salary;
	
	@Column(name="maxSalary")
	private BigDecimal maxSalary;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private Gender gender;

	@Column(name="employer", nullable = true)
	private String employer;
	
	@Column(name="timestamp")
	private Date timestamp;
	
	@Column(name="location", nullable = true)
	private String location;
	
	@Column(name="yearAtEmployer", nullable = true)
	private String yearsAtEmployer;
	
	@Column(name="yearOfExperience", nullable = true)
	private String yearsOfExperience;
	
	@Column(name="signingBonus", nullable = true)
	private String signingBonus;
	
	@Column(name="annualStockValue", nullable = true)
	private String annualStockValue;
	
	@Column(name="comments", nullable = true, columnDefinition = "varchar(1000)")
	private String comments;
	
	@Column(name="annualBonus", nullable = true)
	private String annualBonus;
}
