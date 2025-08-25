package com.ucorp.catalog.web;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucorp.catalog.dto.BookCreateRequestDTO;
import com.ucorp.catalog.dto.BookDetailResponseDTO;
import com.ucorp.catalog.dto.BookListResponseDTO;
import com.ucorp.catalog.dto.BookUpadateRequestDto;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.service.BookService;
import com.ucorp.catalog.service.impl.BookServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
public class BookResource {

	private final BookService bookService;

	
	@GetMapping("/v1/book/{id}")
	public ResponseEntity<BookDetailResponseDTO>findBookDetail(@PathVariable ("id")String id){
		StopWatch stopWatch = new StopWatch();
		log.info("start findBookDetail"+id);
		stopWatch.start();
	    BookDetailResponseDTO	result = bookService.findBookDetailById(id);
	    log.info("Finish findBookDetail. execution time ={}",stopWatch.getTime());
	    return ResponseEntity.ok(result);
	}
	
	@PostMapping("/v1/book")
	public ResponseEntity<Void> createANewBook(@RequestBody BookCreateRequestDTO dto){
		bookService.createNewBook(dto);
		return	ResponseEntity.created(URI.create("/book")).build();
	}

	@GetMapping("/v1/book")
	public ResponseEntity<List<BookDetailResponseDTO>>findBookList(){
		return ResponseEntity.ok(bookService.findBookList());
	}
	
	//get book list->
	// 1.judul buku
	// 2.nama penerbit dari tabel publisher
	// 3. nama penulis dari table auhtor
	//untuk itu gunakan JPQL
	
	@GetMapping("/v2/book")
	public ResponseEntity<ResultPageResponDTO<BookListResponseDTO>>findBookList(
			@RequestParam(name = "page",required = true,defaultValue = "0") Integer page,
			@RequestParam(name = "limit",required = true, defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy",required = true,defaultValue = "title") String sortBy,
			@RequestParam(name = "direction",required =true,defaultValue = "asc")  String direction,
			@RequestParam(name = "bookTitle",required = false,defaultValue = "") String bookTitle,
			@RequestParam(name = "publisherName",required = false,defaultValue = "") String publisgherName,
			@RequestParam(name = "authorName",required = false,defaultValue = "")String authorName
			){
		return ResponseEntity.ok().body(bookService.findBookList(page,limit,sortBy,direction,publisgherName,bookTitle,authorName));
	}
	
	@PutMapping("/v1/book/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId,
			@RequestBody BookUpadateRequestDto dto){
		bookService.updateBook(bookId, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/v1/book/{bookId}")
	public ResponseEntity<Void>deleteBook(@PathVariable("bookId") Long bookId){
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}
	
	
}
