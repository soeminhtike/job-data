package com.ata.job.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class AbstractEntity {

	@Id
	@Column(name = "Id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="created_date", updatable = false)
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@PrePersist
	public void prepersist() {
		createdDate = new Date();
		updatedDate = new Date();
	}
	
	@PreUpdate
	public void preupdate() {
		updatedDate = new Date();
	}
}
