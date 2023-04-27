package com.ata.job.dao;

import java.util.List;

import com.ata.job.entity.AbstractEntity;
import com.ata.job.search.EntitySearch;

public interface CustomSearchDao {

	public <T extends AbstractEntity> List<T> search(EntitySearch entitySearch, Class<T> targetClass);
}
