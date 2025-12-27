package com.example.lendingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="borrow")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // --- Foreign keys ---
    private Integer studentId;
    private Integer bookId;

    // --- Join the Book table ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Book book;

    // --- Dates ---
    private String dateBorrowed;
    private String dateDue;
    private String dateReturned;

    // --- Penalty ---
    private Double penalty;

    public Borrow(){}

    public Borrow(Integer studentId, Integer bookId, String dateBorrowed) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.dateBorrowed = dateBorrowed;
    }

    // ===== GETTERS =====
    public Integer getId() { return id; }
    public Integer getStudentId() { return studentId; }
    public Integer getBookId() { return bookId; }
    public Book getBook() { return book; }
    public String getDateBorrowed() { return dateBorrowed; }
    public String getDateDue() { return dateDue; }
    public String getDateReturned() { return dateReturned; }
    public Double getPenalty() { return penalty; }

    // ===== SETTERS =====
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public void setDateBorrowed(String dateBorrowed) { this.dateBorrowed = dateBorrowed; }
    public void setDateDue(String dateDue) { this.dateDue = dateDue; }
    public void setDateReturned(String dateReturned) { this.dateReturned = dateReturned; }
    public void setPenalty(Double penalty) { this.penalty = penalty; }
}
