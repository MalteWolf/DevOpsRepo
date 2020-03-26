package encobib.service;

import java.util.List;

import encobib.model.Book;

public interface BooksService {

    List<Book> getAllBooks();

    Book createOrUpdateBook(Book book);

    Book getBookById(Integer id);

    void deleteBookById(int id);
    //
    //    List<LendingPeriod> getLendingPeriodsByBookId(int id);
    //
    //    LendingPeriod addNewLendingPeriod(int id, LendingPeriod lendingPeriod);
}
