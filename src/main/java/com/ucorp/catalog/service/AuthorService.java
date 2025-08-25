package com.ucorp.catalog.service;

import java.util.List;
import java.util.Map;

import com.ucorp.catalog.domain.Author;
import com.ucorp.catalog.dto.AuthorCreateRequestDto;
import com.ucorp.catalog.dto.AuthorResponseDTO;
import com.ucorp.catalog.dto.AuthorUpdateRequestDTO;

public interface AuthorService {

	public AuthorResponseDTO findAuthorById(String id);

	public void createNewAuthor(List<AuthorCreateRequestDto> dto);

	public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto);
	
	public void deleteAuthor(String authorId);
	
	public List<Author> findAuthors(List<String>authorIdList);
	
	public List<AuthorResponseDTO>contructorDTO(List<Author>authors); 
	
	public Map<Long, List<String>> findAuthorMaps (List<Long> bookIdList);
}
