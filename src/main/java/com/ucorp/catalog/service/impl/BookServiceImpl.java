package com.ucorp.catalog.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.ucorp.catalog.controller.BookController;
import com.ucorp.catalog.domain.Author;
import com.ucorp.catalog.domain.Book;
import com.ucorp.catalog.domain.Category;
import com.ucorp.catalog.domain.Publisher;
import com.ucorp.catalog.dto.BookCreateRequestDTO;
import com.ucorp.catalog.dto.BookDetailResponseDTO;
import com.ucorp.catalog.dto.BookListResponseDTO;
import com.ucorp.catalog.dto.BookQueryDTO;
import com.ucorp.catalog.dto.BookUpadateRequestDto;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.exception.BadRequestException;
import com.ucorp.catalog.repository.AuthorRepository;
import com.ucorp.catalog.repository.BookRepository;
import com.ucorp.catalog.service.AuthorService;
import com.ucorp.catalog.service.BookService;
import com.ucorp.catalog.service.CategoryService;
import com.ucorp.catalog.service.PublisherService;
import com.ucorp.catalog.util.PaginationUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;

	private final AuthorService authorService;
	
	private final CategoryService categoryService;
	
	private final PublisherService publisherService;

	
	@Override
	public BookDetailResponseDTO findBookDetailById(String bookId) {
		System.out.println("Halo");
		log.info("Hallo");
		log.info("=== START GET DATA BOOK ===");
		Book book = bookRepository.findBySecureId(bookId).orElseThrow(() -> new BadRequestException("invalid book id"));
		log.info("=== FINISH GET DATA BOOK ===");

		BookDetailResponseDTO dto = new BookDetailResponseDTO();
		dto.setBookId(book.getSecureId());
		
		log.info("=== START GET DATA CATEGORIES ===");
		dto.setCategories(categoryService.constructDTO(book.getCategories()));
		log.info("=== FINISH GET DATA CATEGORIES ===");

		log.info("=== START GET DATA PUBLISHER ===");
		dto.setPublisher(publisherService.contructDTO(book.getPublisher()));
		log.info("=== START GET DATA PUBLISHER ===");

		log.info("=== START GET DATA AUTHOR ===");
		dto.setAuthors(authorService.contructorDTO(book.getAuthors()));
		log.info("=== FINISH GET DATA AUTHOR ===");

		dto.setBookTitle(book.getTitle());
		dto.setBookDescription(book.getDescription());
		return dto;
	}

	@Override
	public List<BookDetailResponseDTO> findBookList() {
		log.info("=== FINISH GET DATA AUTHOR ===");
		List<Book> books = bookRepository.findAll();
		return books.stream().map((b) -> {
			BookDetailResponseDTO dto = new BookDetailResponseDTO();
//		dto.setAuthorName(b.getAuthor().getName());
			dto.setAuthors(authorService.contructorDTO(b.getAuthors()));
			dto.setCategories(categoryService.constructDTO(b.getCategories()));
			dto.setPublisher(publisherService.contructDTO(b.getPublisher()));
			dto.setBookDescription(b.getDescription());
			dto.setBookId(b.getSecureId());
			dto.setBookTitle(b.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateRequestDTO dto) {
		List<Author>authors = authorService.findAuthors(dto.getAuthorIdList());
		List<Category>categories = categoryService.findCategories(dto.getCategoryList());
		Publisher publisher = publisherService.findPublisher(dto.getPublisherId());

		Book book = new Book();
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublisher(publisher);
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		bookRepository.save(book);

	}

	@Override
	public void updateBook(Long bookId, BookUpadateRequestDto dto) {
		// get Book Dulu dari Repo
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BadRequestException("invalid book id method update Book"));
		// update
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		// save
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);

		// TODO Auto-generated method stub

	}

	@Override
	public ResultPageResponDTO<BookListResponseDTO> findBookList(Integer page, Integer limit, String sortBy,
		String direction, String publisherName, String bookTitle,String authorName) {
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction),sortBy));
		Pageable pageable = PageRequest.of(page,limit, sort);
		Page<BookQueryDTO>pageResult = bookRepository.findBookList(bookTitle, publisherName,authorName, pageable);
		List<Long>idList= pageResult.stream().map(b-> b.getId()).collect(Collectors.toList());
		Map<Long, List<String>> categoryMap = categoryService.findCategoriesMap(idList);
	    Map<Long, List<String>>	authorMap = authorService.findAuthorMaps(idList);
		List<BookListResponseDTO>dtos = pageResult.stream().map(b->{
			BookListResponseDTO dto = new BookListResponseDTO();
			dto.setAuthorName(authorMap.get(b.getId()));
			dto.setCategoryCode(categoryMap.get(b.getId()));
			dto.setTitle(b.getBookTitle());
			dto.setPubsliherName(b.getPublisherName());
			dto.setDescription(b.getDescription());
			dto.setId(b.getBookId());
			return dto;
		}).collect(Collectors.toList());
		
		
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(),pageResult.getTotalPages());
	}

}
