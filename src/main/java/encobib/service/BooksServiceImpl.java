package encobib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import encobib.exceptions.BookNotFoundException;
import encobib.model.Book;
import encobib.model.LendingPeriod;
import encobib.repository.PostGreSQLBooksRepository;
import encobib.repository.PostGreSqlLendingPeriodsRepository;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    private final PostGreSQLBooksRepository booksRepository;
    private final PostGreSqlLendingPeriodsRepository lendindPeriodRepository;

    BooksServiceImpl(PostGreSQLBooksRepository booksRepository, PostGreSqlLendingPeriodsRepository lendindPeriodRepository) {
        this.booksRepository = booksRepository;
        this.lendindPeriodRepository = lendindPeriodRepository;
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
                if (!validateIsbn(book.getIsbn())) {
                    throw new IllegalArgumentException("Error! The ISBN is not valid");
                }
                if (book.getTitle().isEmpty()) {
                    throw new IllegalArgumentException("Error! Please enter a title");
                }
                if (checkIfIsbnOrTitleExists(book.getIsbn(), book.getTitle())) {
                    throw new IllegalArgumentException("Error! The ISBN or the title already exists");
                }
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


        @Override
        public Optional<LendingPeriod> getLendingPeriodsByBookId(int id) {
            getBookById(id);
            return lendindPeriodRepository.findById(id);
        }


}





