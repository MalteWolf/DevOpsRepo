package encobib.repository;

import encobib.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGreSQLBooksRepository extends CrudRepository<Book, Integer> {

}
