package com.example.lendingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;
    private int quantity;

    @Column(columnDefinition="TEXT")
    private String description;

    public Book(){}

    public Book(String title, String author, int quantity, String description){
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.description = description;
    }

    public Integer getId(){ return id; }
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public int getQuantity(){ return quantity; }
    public String getDescription(){ return description; }

    public void setId(Integer id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setAuthor(String author){ this.author = author; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
    public void setDescription(String description){ this.description = description; }
}
