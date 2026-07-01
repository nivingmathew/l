package com.cdac.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.cdac.model.Book;

@Transactional
public class BookDAO {
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
    }

    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Book", Book.class)
                .list();
    }

    public List<Book> searchByCategory(String category) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Book where category = :cat", Book.class)
                .setParameter("cat", category)
                .list();
    }

    public List<Book> searchById(int bookId) {
        Book b = sessionFactory.getCurrentSession().get(Book.class, bookId);
        List<Book> list = new ArrayList<>();
        if(b != null) {
            list.add(b);
        }
        return list;
    }

    public void deleteBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book b = session.get(Book.class, bookId);
        if(b != null) {
            session.delete(b);
        }
    }
}