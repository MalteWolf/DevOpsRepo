package encobib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import encobib.exceptions.BookNotFoundException;
import encobib.model.Book;
import encobib.repository.PostGreSQLBooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BooksServiceImpl implements BooksService {

    private final PostGreSQLBooksRepository booksRepository;

    BooksServiceImpl(PostGreSQLBooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> result = (List<Book>) booksRepository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public Book getBookById(Integer id) {
        Optional<Book> employee = booksRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new BookNotFoundException(id);
        }
    }

    public Book createOrUpdateBook(Book book) {
        //        if (!validateIsbn(book.getIsbn())) {
        //            throw new IllegalArgumentException("Error! The ISBN is not valid");
        //        }
        //        if (book.getTitle().isBlank()) {
        //            throw new IllegalArgumentException("Error! Please enter a title");
        //        }
        //        if (checkIfIsbnOrTitleExists(book.getIsbn(), book.getTitle())) {
        //            throw new IllegalArgumentException("Error! The ISBN or the title already exists");
        //        }
        if (book.getId() == null) {
            book = booksRepository.save(book);
            return book;
        } else {
            Optional<Book> employee = booksRepository.findById(book.getId());
            if (employee.isPresent()) {
                Book newBook = employee.get();
                newBook.setTitle(book.getTitle());
                newBook.setAuthor(book.getAuthor());
                newBook.setIsbn(book.getIsbn());
                newBook.setPublisher(book.getPublisher());
                newBook.setAvailable(book.getAvailable());
                newBook.setLocation(book.getLocation());
                newBook = booksRepository.save(newBook);
                return newBook;
            } else {
                book = booksRepository.save(book);
                return book;
            }
        }
    }

    public void deleteBookById(int id) throws BookNotFoundException {
        validateBookId(id);
        booksRepository.deleteById(id);
    }

    /*      Validate ISBN-10 AND ISBN-13 Formats Both Definition:
            ^
                (?:ISBN(?:-1[03])?:?\ )?  # Optional ISBN/ISBN-10/ISBN-13 identifier.
                (?=                       # Basic format pre-checks (lookahead):
                [0-9X]{10}$               # Require 10 digits/Xs (no separators).
                |                         # Or:
                (?=(?:[0-9]+[-\ ]){3})    # Require 3 separators
                [-\ 0-9X]{13}$            # out of 13 characters total.
                |                         # Or:
                97[89][0-9]{10}$          # 978/979 plus 10 digits (13 total).
                |                         # Or:
                (?=(?:[0-9]+[-\ ]){4})    # Require 4 separators
                [-\ 0-9]{17}$             # out of 17 characters total.
                )                         # End format pre-checks.
                (?:97[89][-\ ]?)?         # Optional ISBN-13 prefix.
                [0-9]{1,5}[-\ ]?          # 1-5 digit group identifier.
                [0-9]+[-\ ]?[0-9]+[-\ ]?  # Publisher and title identifiers.
                [0-9X]                    # Check digit.
            $
    */

    boolean validateIsbn(String isbn) {
        String regex =
            "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89]"
                + "[- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }

    private boolean checkIfIsbnOrTitleExists(String isbn, String title) {
        List<Book> books = getAllBooks();
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) || (book.getTitle().equals(title))) {
                return true;
            }
        }
        return false;
    }

    private void validateBookId(int id) {
        if (id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error! Please enter a valid, non negative ID");
        }
    }

    //    @Override
    //    public List<LendingPeriod> getLendingPeriodsByBookId(int id) {
    //        getBookById(id);
    //        return booksRepository.getLendingPeriodsByBookId(id);
    //    }
    //
    //    @Override
    //    public LendingPeriod addNewLendingPeriod(int id, LendingPeriod lendingPeriod) {
    //        if (lendingPeriod.getStartDate() == null) {
    //            throw new IllegalArgumentException("Error! The startdate can´t be empty");
    //        }
    //        List<LendingPeriod> lendingPeriodList = getLendingPeriodsByBookId(id);
    //        if (!lendingPeriodList.isEmpty() && lendingPeriodList.get(lendingPeriodList.size() - 1).getEndDate() == null) {
    //            throw new IllegalArgumentException("Error! The book is already on loan");
    //        }
    //        if (lendingPeriod.getEndDate() == null || lendingPeriod.getStartDate().isBefore(lendingPeriod.getEndDate())) {
    //            lendingPeriod.setBook(new Book(id));
    //            return booksRepository.addNewLendingPeriod(id, lendingPeriod);
    //        } else
    //            throw new IllegalArgumentException("Error! The lending period startdate has to be before the enddate");
    //    }
}





