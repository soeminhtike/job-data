package com.ata.job.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ata.job.controller.JobApiController.Sorter;

public class CommonUtils {
	
	public static Sorter jobSorter = (type, jobList, sort) -> {
		Function<Integer, Integer> changer =  type.equals("ASC") ?  num -> num : Math::negateExact;
		jobList.sort((j1, j2) -> changer.apply(sort.apply(j1,j2)));
	};

	public static <T> List<T> toList(Iterable<T> iterable) {
		if (iterable == null)
			return Collections.emptyList();
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	} 
}
