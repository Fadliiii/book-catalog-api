package com.ucorp.catalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucorp.catalog.dto.BookCreateRequestDTO;
import com.ucorp.catalog.dto.BookDetailResponseDTO;
import com.ucorp.catalog.service.BookService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/list")
	public String findBookList(org.springframework.ui.Model model) {
		List<BookDetailResponseDTO>books =  bookService.findBookList();
		model.addAttribute("books",books);
		return "book/list";
	}
	
	
	@GetMapping("/new")
	public String loadBookForm(Model model) {
		BookCreateRequestDTO dto = new BookCreateRequestDTO();
		model.addAttribute("bookCreateDto",dto);
		return "/book/book-new";
	}
	
	@PostMapping("/new")
	public String addNewBook(@ModelAttribute("bookCreateDto")@Valid BookCreateRequestDTO dto,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("bookCreateDto",dto);
			return "/book/book-new";
		}
		bookService.createNewBook(dto);
		return "redirect:/book/list";
	}
	
	
}
