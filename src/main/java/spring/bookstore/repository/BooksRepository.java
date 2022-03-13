package spring.bookstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.bookstore.model.Book;

public interface BooksRepository extends CrudRepository<Book, Integer> {

    Book findByName(String name);

    @Override
    @Query("select distinct b from Book b join fetch b.author")
    Iterable<Book> findAll();
}
