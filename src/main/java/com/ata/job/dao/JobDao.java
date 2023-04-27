package com.ata.job.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ata.job.entity.Job;

@Repository
public interface JobDao extends CrudRepository<Job, Long> {
	
}
