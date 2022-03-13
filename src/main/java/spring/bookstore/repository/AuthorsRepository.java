package spring.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import spring.bookstore.model.Author;

public interface AuthorsRepository extends CrudRepository<Author, Integer> {

    Author findByName(String name);
}
