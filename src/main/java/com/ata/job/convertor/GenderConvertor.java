package com.ata.job.convertor;

import java.util.List;

import com.ata.job.constant.Gender;

import jakarta.persistence.AttributeConverter;

public class GenderConvertor implements AttributeConverter<List<Gender>, String> {

	@Override
	public String convertToDatabaseColumn(List<Gender> attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gender> convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return null;
	}

}
