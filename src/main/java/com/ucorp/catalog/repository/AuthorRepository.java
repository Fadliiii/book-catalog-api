package com.ucorp.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucorp.catalog.domain.Author;
import com.ucorp.catalog.dto.AuthorQueryDTO;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	
	public List<Author>findBySecureIdIn(List<String> authorIdList);

	// method name convetion
	// find+keyword
	// sql -> select * from Author a where a.id=
   //public Optional<Author>findById(Long id);

	public Optional<Author>findBySecureId(String secureId);
	
	// where id = id dan deleted = false
	public Optional<Author> findByIdAndDeletedFalse(Long id);

	// sql -> select a from Author a where a.name Like : authorId
	public List<Author> findByNameLike(String authorName);
	
	@Query( " SELECT new com.ucorp.catalog.dto.AuthorQueryDTO(b.id,ba.name)"
			+" FROM Book b"
			+" JOIN b.authors ba"
			+" WHERE b.id IN :bookIdList ")
	public List<AuthorQueryDTO> findAuthorsByBookIdList(List<Long>bookIdList);
	
}
