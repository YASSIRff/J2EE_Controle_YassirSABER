package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BookService {

	@Autowired
	private BookRepository bRepo;

	public void save(Book book) {
		bRepo.save(book);
	}


	public List<Book> getAllBook() {
		return bRepo.findAll();
	}

	public Book getBookById(int id) {
		return bRepo.findById(id).orElse(null);
	}

	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
}
