package com.ucorp.catalog.service;

import java.util.List;

import com.ucorp.catalog.dto.BookCreateRequestDTO;
import com.ucorp.catalog.dto.BookDetailResponseDTO;
import com.ucorp.catalog.dto.BookListResponseDTO;
import com.ucorp.catalog.dto.BookUpadateRequestDto;
import com.ucorp.catalog.dto.ResultPageResponDTO;

import lombok.extern.slf4j.Slf4j;

public interface BookService {

	public BookDetailResponseDTO findBookDetailById(String bookId);

	public List<BookDetailResponseDTO> findBookList();

	public void createNewBook(BookCreateRequestDTO dto);

	public void updateBook(Long bookId, BookUpadateRequestDto dto);

	public void deleteBook(Long bookId);

	public ResultPageResponDTO<BookListResponseDTO>findBookList(Integer page,Integer limit, String sortBy,String direction, String publisherName, String bookTitle,String authorName);
}
