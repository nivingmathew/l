package com.cdac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @Column(name = "bookId")
    private int bookId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "price")
    private double price;

    public Book() {}

    public Book(int bookId, String title, String author, String category, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    // Keep all your exact same Getters and Setters here...
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}