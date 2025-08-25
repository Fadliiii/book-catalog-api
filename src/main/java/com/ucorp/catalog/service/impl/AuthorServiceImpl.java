package com.ucorp.catalog.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.ucorp.catalog.domain.Address;
import com.ucorp.catalog.domain.Author;
import com.ucorp.catalog.dto.AuthorCreateRequestDto;
import com.ucorp.catalog.dto.AuthorQueryDTO;
import com.ucorp.catalog.dto.AuthorResponseDTO;
import com.ucorp.catalog.dto.AuthorUpdateRequestDTO;
import com.ucorp.catalog.repository.AuthorRepository;
import com.ucorp.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	@Override
	public AuthorResponseDTO findAuthorById(String id) {
		// TODO Auto-generated method stub

		// 1. fecth data from database
		Author author = authorRepository.findBySecureId(id)
				.orElseThrow(() -> new com.ucorp.catalog.exception.ResourceNotFoundException(
						"invalid authorId" + " Method findById"));

		// 2. author -> authorResponseDTO
		AuthorResponseDTO dto = new AuthorResponseDTO();
		dto.setAuthorName(author.getName());
		dto.setBirthDate(author.getBirthDate().toEpochDay());

		return dto;
	}

	@Override
	public void createNewAuthor(List<AuthorCreateRequestDto> dtos) {

		List<Author> authors = dtos.stream().map((dto) -> {
			Author author = new Author();
			author.setName(dto.getAuthorName());
			author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));

			// menyisipkan address ke author
			List<Address> addresses = dto.getAddresses().stream().map(a -> {
				Address address = new Address();
				address.setCityName(a.getCityName());
				address.setStreetName(a.getStreetName());
				address.setZipCode(a.getZipCode());
				address.setAuthor(author);
				return address;
			}).collect(Collectors.toList());

			author.setAddresses(addresses);
			return author;
		}).collect(Collectors.toList());
		authorRepository.saveAll(authors);
	}

	@Override
	public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto) {
		Author author = authorRepository.findBySecureId(authorId).orElseThrow(
				() -> new com.ucorp.catalog.exception.BadRequestException("Invalid id not found" + " Method Update"));
		Map<Long, Address> addressMap = author.getAddresses().stream().map(a -> a)
				.collect(Collectors.toMap(Address::getId, Function.identity()));
		List<Address>addresses= dto.getAddresses().stream().map(a->{
			Address address= addressMap.get(a.getId());
			address.setCityName(a.getCityName());
			address.setStreetName(a.getStreetName());
			address.setZipCode(a.getZipCode());
			return address;
		}).collect(Collectors.toList());
		author.setAddresses(addresses);
		author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
		author.setBirthDate(
				dto.getBirthDate() == null ? author.getBirthDate() : LocalDate.ofEpochDay(dto.getBirthDate()));

		authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(String authorId) {
		Author author = authorRepository.findBySecureId(authorId).orElseThrow(
				() -> new com.ucorp.catalog.exception.BadRequestException("Invalid id not found" + " Method Update"));
		authorRepository.delete(author);
		// 1 select data penulis ada atau tidak or langsung delete tanpa cek datanya ada
		// atau tidak

//		 //1. ambil datanya dulu deleted= false
//		Author author = authorRepository.findByIdAndDeletedFalse(authorId)
//			.orElseThrow(()-> new com.ucorp.catalog.exception.BadRequestException("invalid id is null deleted method Author"));
//		 //2 update deleted = true
//		 author.setDeleted(Boolean.TRUE);
//		 authorRepository.save(author);

		// 2 delete(hard delete)
//		authorRepository.deleteById(authorId);
	}

	@Override
	public List<Author> findAuthors(List<String> authorIdList) {
		List<Author> authors = authorRepository.findBySecureIdIn(authorIdList);
		if (authors.isEmpty())
			throw new com.ucorp.catalog.exception.BadRequestException("Authors cant Empty");
		return authors;
	}

	@Override
	public List<AuthorResponseDTO> contructorDTO(List<Author> authors) {
		return authors.stream().map((a) -> {
			AuthorResponseDTO dto = new AuthorResponseDTO();
			dto.setAuthorName(a.getName());
			dto.setBirthDate(a.getBirthDate().toEpochDay());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Long, List<String>> findAuthorMaps(List<Long> bookIdlist) {
		// TODO Auto-generated method stub
		
		List<AuthorQueryDTO>queryList = authorRepository.findAuthorsByBookIdList(bookIdlist);
		Map<Long,List<String>> authorMap = new HashMap<>();
		List<String> authorList = null;
		for(AuthorQueryDTO q:queryList) {
			if(!authorMap.containsKey(q.getBookId())) {
				authorList = new ArrayList<>();
			}else {
				authorList = authorMap.get(q.getBookId());
			}
		authorList.add(q.getAuthorName());
		authorMap.put(q.getBookId(), authorList);
		}
		return authorMap;
	}

}
