package com.example.lendingsystem.controller;

import com.example.lendingsystem.model.Book;
import com.example.lendingsystem.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    private final BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    @GetMapping
    public List<Book> list(){
        return service.allBooks();
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        return service.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Integer id, @RequestBody Book book){
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
