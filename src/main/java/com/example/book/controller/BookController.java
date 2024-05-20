package com.example.book.controller;


import com.example.book.entity.Book;
import com.example.book.entity.MyBookList;
import com.example.book.service.BookService;
import com.example.book.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
@Controller
public class BookController {

	@Autowired
	private BookService service;


	@Autowired
	private MyBookListService myBookService;

	@GetMapping("/Book")
	public String Book() {
		return "Book";
	}

	@GetMapping("/book_register")
	public String BookRegister(Model model) {
		model.addAttribute("book", new Book());
		return "bookRegister";
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute("book") Book book, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				book.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.save(book);
		return "redirect:/available_books";
	}


	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book>list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}

	@GetMapping("/my_books")
	public String getMyBooks(Model model)
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getImage(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}

	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				book.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.save(book);
		return "redirect:/available_books";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
}
