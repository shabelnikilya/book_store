package spring.bookstore.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.bookstore.model.Book;
import spring.bookstore.repository.BooksRepository;

@org.springframework.stereotype.Service
public class BooksService implements Service<Book> {
    private final BooksRepository repository;

    @Autowired
    public BooksService(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Book findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}
