package de.uni.koeln.se.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	// #1 Find all books
	public List<Book> findBooks() {
		return bookRepo.findAll();
	}
	
	// #2 Find a book by its ID
	public Optional<Book> fetchBook(int id) {
		return bookRepo.findById(id);
	}
	
	// #3 Add a new book to the DB
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	// #4 Delete a book from the DB
	public boolean deleteBook(int id) {
		boolean status;
		try {
			bookRepo.deleteById(id);
			status = true;
		}catch (Exception e) {
			status = false;
		}
		return status;
	}
	
	// Exercise 2
	// #5 Get oldest book
	public Book getOldestBook() {
		List<Book> books = new ArrayList<Book>();
		books = findBooks();
		Book oldestBook = books.get(0);
		for(int i = 0; i < books.size(); i++) {
			if(oldestBook.getYear() > books.get(i).getYear()) {
				oldestBook = books.get(i);
			}
		}
		return oldestBook;
	}
	
	// #6 Get latest book
	public Book getLatestBook() {
		List<Book> books = new ArrayList<Book>();
		books = findBooks();
		Book latestBook = books.get(0);
		for(int i = 0; i < books.size(); i++) {
			if(latestBook.getYear() < books.get(i).getYear()) {
				latestBook = books.get(i);
			}
		}
		return latestBook;
	}
}
