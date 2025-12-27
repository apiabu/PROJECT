package com.example.lendingsystem.service;

import com.example.lendingsystem.model.Borrow;
import com.example.lendingsystem.repository.BorrowRepository;
import com.example.lendingsystem.service.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository repo;
    private final BookService bookService;

    public BorrowService(BorrowRepository repo, BookService bookService) {
        this.repo = repo;
        this.bookService = bookService;
    }

    public List<Borrow> list(){
        return repo.findAll();
    }

    public Borrow borrow(Borrow borrowObj){

        LocalDate now = LocalDate.now();
        LocalDate dueDate = now.plusDays(7);

        borrowObj.setDateBorrowed(now.toString());
        borrowObj.setDateDue(dueDate.toString());
        borrowObj.setPenalty(0.0);

        bookService.decrementQuantity(borrowObj.getBookId());

        return repo.save(borrowObj);
    }

    public Borrow returnBook(Integer borrowId){

        return repo.findById(borrowId).map(borrow -> {

            LocalDate now = LocalDate.now();
            borrow.setDateReturned(now.toString());

            LocalDate due = LocalDate.parse(borrow.getDateDue());
            if(now.isAfter(due)){
                long daysLate = ChronoUnit.DAYS.between(due, now);
                borrow.setPenalty(daysLate * 10.0);
            }

            bookService.incrementQuantity(borrow.getBookId());

            return repo.save(borrow);

        }).orElse(null);
    }
}
