package com.ata.job.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.job.dao.CustomSearchDao;
import com.ata.job.dao.JobDao;
import com.ata.job.entity.Job;
import com.ata.job.search.EntitySearch;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private CustomSearchDao customSearchDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Override
	public List<Job> search(EntitySearch search) {
		if(search.isEmpty())
			return Collections.emptyList();
		
		return customSearchDao.search(search, Job.class);
	}

	@Override
	public void save(Job job) {
		jobDao.save(job);
	}

}
