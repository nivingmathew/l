package com.cdac.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cdac.dao.BookDAO;
import com.cdac.model.Book;

@Controller
public class BookController {
    private BookDAO bookDAO;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam int bookId, @RequestParam String title, @RequestParam String author, @RequestParam String category, @RequestParam double price, Model model) {
        Book book = new Book(bookId, title, author, category, price);
        bookDAO.save(book);
        model.addAttribute("msg", "Book Added Successfully");
        return "success";
    }

    @GetMapping("/view")
    public String viewBooks(Model model) {
        List<Book> books = bookDAO.getAllBooks();
        model.addAttribute("books", books);
        return "viewbooks";
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "searchbook";
    }

    @PostMapping("/processSearch")
    public String processSearch(@RequestParam String category, Model model) {
        List<Book> books = bookDAO.searchByCategory(category);
        model.addAttribute("books", books);
        return "viewbooks"; // Reusing the viewbooks page to show results!
    }

    @GetMapping("/delete")
    public String showDeleteForm() {
        return "deletebook";
    }

    @PostMapping("/processDelete")
    public String processDelete(@RequestParam int bookId, Model model) {
        bookDAO.deleteBook(bookId);
        model.addAttribute("msg", "Book Deleted Successfully");
        return "success";
    }
}