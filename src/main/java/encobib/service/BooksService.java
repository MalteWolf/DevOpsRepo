package encobib.service;

import java.util.List;
import java.util.Optional;

import encobib.model.Book;
import encobib.model.LendingPeriod;

public interface BooksService {

    List<Book> getAllBooks();

    Book createOrUpdateBook(Book book);

    Book getBookById(Integer id);

    void deleteBookById(int id);

    Optional<LendingPeriod> getLendingPeriodsByBookId(int id);

//    LendingPeriod addNewLendingPeriod(int id, LendingPeriod lendingPeriod);
}
