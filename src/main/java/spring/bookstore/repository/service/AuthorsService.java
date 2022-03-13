package spring.bookstore.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.bookstore.model.Author;
import spring.bookstore.repository.AuthorsRepository;

@org.springframework.stereotype.Service
public class AuthorsService implements Service<Author> {
    private final AuthorsRepository repository;

    @Autowired
    public AuthorsService(AuthorsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Author findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Author findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}
