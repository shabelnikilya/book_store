package spring.bookstore.repository.service;

public interface Service<T> {

    Iterable<T> findAll();

    T findById(int id);

    T findByName(String name);

    T save(T t);

    void remove(int id);
}
