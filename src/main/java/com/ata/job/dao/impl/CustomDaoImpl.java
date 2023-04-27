package com.ata.job.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.job.dao.CustomSearchDao;
import com.ata.job.entity.AbstractEntity;
import com.ata.job.search.EntitySearch;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class CustomDaoImpl implements CustomSearchDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public <T extends AbstractEntity> List<T> search(EntitySearch entitySearch, Class<T> targetClass) {
		TypedQuery<T> query = entityManager.createQuery(entitySearch.createQuery(), targetClass);
		List<T> searchResult = query.getResultList();
		
		return CollectionUtils.isNotEmpty(searchResult) ? searchResult : Collections.emptyList();
	}

}
