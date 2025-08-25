package com.ucorp.catalog.config;

import java.security.Key;
import java.util.Base64.Decoder;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucorp.catalog.security.util.JwtTokenFactory;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.ucorp.catalog.domain.Author;
//import com.ucorp.catalog.domain.Book;
//import com.ucorp.catalog.repository.BookRepository;
//import com.ucorp.catalog.repository.impl.BookRepositoryImpl;
//
@Configuration
public class AppConfig {

	@Bean
	public SecretKey key() {
		byte[] keyBytes = Decoders.BASE64.decode("wweewqeqwdqhdoiufugeubujbuiqbwuiguqwvbewgd123213129834u032wqqewqe");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Bean
	public JwtTokenFactory jwtTokenFactory(Key secret) {
		return new JwtTokenFactory(secret);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}

	
//	@Bean
//	public Author author() {
//		return new Author(1L,"Pramoedya Ananta Toer ", null,false);
//	}
//
//	@Bean
//	public Book book1(Author author) {
//		Book book =	new Book();
//		book.setId(1L);
//		book.setTitle("Bumi Manusia");
//		book.setDescription("Bumi Manusia adalah salah satu Novel karya Pramoedya Ananta Toer");
//		book.setAuthor(author);
//		return book;
//	}
//	
//	@Bean
//	public Book book2(Author author) {
//		Book book =	new Book();
//		book.setId(2L);
//		book.setTitle("Arok Dedes");
//		book.setDescription("Arok Dedes adalah salah satu Novel karya Pramoedya Ananta Toer");
//		book.setAuthor(author);
//		return book;
//	}
//	
//	@Bean
//	public BookRepository bookRepository(Book book1,Book book2) {
//		Map<Long,Book> bookmap= new HashMap<>();
//		bookmap.put(1L, book1);
//		bookmap.put(2L, book2);
//		
//		BookRepositoryImpl bookRepositoryImpl = new BookRepositoryImpl();
//		bookRepositoryImpl.setBookMap(bookmap);
//		
//		return bookRepositoryImpl;

//		
//	}
