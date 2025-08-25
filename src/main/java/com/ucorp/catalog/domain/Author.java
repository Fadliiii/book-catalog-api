package com.ucorp.catalog.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "author",indexes ={
		@Index(name="uk_author_secure_id",columnList = "secure_id")
})

//@SQLDelete(sql = "UPDATE author set deleted = true Where id=?")
//@Where(clause = "deleted = false")

public class Author extends AbstarctBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8146153347218253886L;

	/**
	 *kalau pake sequence -> enable Batch insert
	 *kalau pake Identity -> disable bisa di 
	 *kali menggunakan Store Procedure pada database 
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "author_generator")
	@SequenceGenerator(name="author_generator",sequenceName ="author_id_seq")
	private Long id;
	
	
	@Column(name = "author_name",nullable = false,columnDefinition = "varchar(300)")
	private String name;

	@Column(name = "birth_date",nullable = false)
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "author",cascade = CascadeType.REMOVE)
	private List<Address>addresses;
}
