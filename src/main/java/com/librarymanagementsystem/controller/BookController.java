package com.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.service.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;

	}

	@GetMapping(value = "/library/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findAllBooks(Model model) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("books", books);
		return books;
	}

	@GetMapping(value = "/library/booksbyId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book findBookById(@PathVariable("id") Long id, Model model) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return book;
	}

	@PostMapping(value = "/library", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}

		bookService.createBook(book);
		return ResponseEntity.ok().build();

	}

	@DeleteMapping(value = "/library/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long id, Model model) {

		bookService.deleteBook(id);
		return ResponseEntity.ok().build();
	}

}
