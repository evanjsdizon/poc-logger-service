package com.cd.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class DateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

	@Override
	public String convert(LocalDateTime object) {
		return object.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	@Override
	public LocalDateTime unconvert(String object) {
		return LocalDateTime.parse(object);
	}

}
