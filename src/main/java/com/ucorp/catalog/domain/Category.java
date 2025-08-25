package com.ucorp.catalog.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name = "category",indexes ={
		@Index(name="uk_category_secure_id",columnList = "secure_id")
})
public class Category extends AbstarctBaseEntity{/**
	 * 
	 */
	private static final long serialVersionUID = -178206420232476966L;

	@Id
	@Column(name = "code",nullable = false)
	private String code;

	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "description",nullable = true)
	private String description;
	
	
	@ManyToMany(mappedBy = "categories")
	private List<Book>books;
	
}
