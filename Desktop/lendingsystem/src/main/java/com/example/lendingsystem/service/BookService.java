package com.example.lendingsystem.service;

import com.example.lendingsystem.model.Book;
import com.example.lendingsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> allBooks() {
        return repo.findAll();
    }

    public Book create(Book book) {
        return repo.save(book);
    }

    public Book update(Integer id, Book updated) {
        return repo.findById(id).map(book -> {
            book.setTitle(updated.getTitle());
            book.setAuthor(updated.getAuthor());
            book.setQuantity(updated.getQuantity());
            return repo.save(book);
        }).orElse(null);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public void decrementQuantity(Integer bookId){
        repo.findById(bookId).ifPresent(book -> {
            if(book.getQuantity() <= 0){
                throw new RuntimeException("Book not available");
            }
            book.setQuantity(book.getQuantity() - 1);
            repo.save(book);
        });
    }

    public void incrementQuantity(Integer bookId){
        repo.findById(bookId).ifPresent(book -> {
            book.setQuantity(book.getQuantity() + 1);
            repo.save(book);
        });
    }
}
