package com.example.lendingsystem.controller;

import com.example.lendingsystem.model.Borrow;
import com.example.lendingsystem.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@CrossOrigin("*")
public class BorrowController {

    private final BorrowService service;

    public BorrowController(BorrowService service){
        this.service = service;
    }

    @GetMapping
    public List<Borrow> list(){
        return service.list();
    }

    @PostMapping
    public Borrow create(@RequestBody Borrow borrow){
        return service.borrow(borrow);
    }

    @PutMapping("/return/{id}")
    public Borrow returnBook(@PathVariable Integer id){
        return service.returnBook(id);
    }
}
