package com.ucorp.catalog.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ucorp.catalog.domain.Book;
import com.ucorp.catalog.dto.BookQueryDTO;


public interface BookRepository extends JpaRepository<Book,Long> {
	
	public Optional<Book> findById(Long id);
	
	//	Saya ingin mencari data buku beradarsarkan secure=id
	//SQL : selecet n from book b where b.secure_id = :id
	//JPQL : select b FROM Book b where b.secureId =:id
	//JPQL merujuk pada class nya bukan tablenya 
	//begitupun dengan columnnya merujuk pada property
	//:id tanda : variable
	
	public Optional<Book> findBySecureId(String id);
	
	
	//SQL : SELECT b from book INNER JOIN publisher P ON p.id = b.publisher_id WHERE p.name = :publisherName AND b.title = :bookTitle
	@Query("""
		    SELECT DISTINCT new com.ucorp.catalog.dto.BookQueryDTO(
		        b.id, b.secureId, b.title, b.publisher.name, b.description
		    )
		    FROM Book b
		    JOIN b.authors ba
		    WHERE LOWER(b.publisher.name) LIKE LOWER(CONCAT('%', :publisherName, '%'))
		    AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%'))
		    AND LOWER(ba.name) LIKE LOWER(CONCAT('%', :authorName, '%'))
		    """)
	public Page<BookQueryDTO> findBookList(String bookTitle,String publisherName,String authorName,Pageable pageable);


	
//	 
//	public List<Book> findAll();
//	
//  public void save(Book book);
//  
//  public void update(Book book);
//
//  public void delete(Long bookId);

}
