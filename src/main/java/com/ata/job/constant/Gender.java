package com.ata.job.constant;

public enum Gender {
	MALE, FEMALE;
	
	public static boolean isValid(String str) {
		return str.toUpperCase().equals(MALE.name()) || str.toUpperCase().equals(FEMALE.name());
	}
	
}
