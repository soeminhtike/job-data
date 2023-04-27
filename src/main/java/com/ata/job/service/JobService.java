package com.ata.job.service;

import java.util.List;

import com.ata.job.entity.Job;
import com.ata.job.search.EntitySearch;

public interface JobService {
	
	public List<Job> search(EntitySearch entitySearch);
	
	public void save(Job job);
}
