//package encobib;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class BooksServiceTest {
//
//    @Mock
//    private BooksRepository booksRepository;
//    private BooksServiceImpl booksService;
//
//    @BeforeEach
//    void setUp() {
//        booksService = new BooksServiceImpl(booksRepository);
//    }
//
//    @Test
//    void getAllBooksReturnsAllBooks() {
//        BookWithoutLendingPeriod testbook0 = new BookWithoutLendingPeriod(0, "ISBN-13: 978-0-596-52068-7", "TestBuch0");
//        BookWithoutLendingPeriod testbook1 = new BookWithoutLendingPeriod(1, "ISBN-13: 978-0-596-52068-8", "TestBuch1");
//        BookWithoutLendingPeriod testbook2 = new BookWithoutLendingPeriod(2, "ISBN-13: 978-0-596-52068-9", "TestBuch2");
//        Collection<BookWithoutLendingPeriod> list = List.of(testbook0, testbook1, testbook2);
//
//        when(booksRepository.getAllBooks()).thenReturn(list);
//
//        Collection<BookWithoutLendingPeriod> books = booksService.getAllBooks();
//        assert (books.containsAll(list));
//    }
//
//    @Test
//    void getBookByIdReturnsBookWhenIdIsValid() {
//        BookWithoutLendingPeriod testBook = mock(BookWithoutLendingPeriod.class);
//
//        when(booksRepository.getBookById(1)).thenReturn(testBook);
//
//        verify(booksRepository, times(2)).getBookById(1);
//        verifyNoMoreInteractions(booksRepository);
//    }
//
//    @Test
//    void getBookByIdThrowsBookNotFoundExceptionWhenIdIsUnknown() {
//        assertThrows(BookNotFoundException.class, () -> booksService.getBookById(11));
//    }
//
//    @Test
//    void getBookByIdThrowsIllegalArgumentExceptionWhenIdIsNegativ() {
//        assertThrows(IllegalArgumentException.class, () -> booksService.validateBookId(-1));
//    }
//
//    @Test
//    void validateIsbnByTestingWithAllVariationsOfValidIsbns() {
//        List<String> isbnsValid = new ArrayList<>();
//        isbnsValid.add("ISBN 978-0-596-52068-7");
//        isbnsValid.add("ISBN-13: 978-0-596-52068-7");
//        isbnsValid.add("978 0 596 52068 7");
//        isbnsValid.add("9780596520687");
//        isbnsValid.add("0-596-52068-9");
//        isbnsValid.add("0 512 52068 9");
//        isbnsValid.add("ISBN-10 0-596-52068-9");
//        isbnsValid.add("ISBN-10: 0-596-52068-9");
//
//        for (String isbn : isbnsValid) {
//            assert (booksService.validateIsbn(isbn));
//        }
//    }
//
//    @Test
//    void validateIsbnByTestingWithNotValidIsbns() {
//        List<String> isbnsNotValid = new ArrayList<>();
//        isbnsNotValid.add("ISBN 11978-0-596-52068-7");
//        isbnsNotValid.add("ISBN-12: 978-0-596-52068-7");
//        isbnsNotValid.add("978 10 596 52068 7");
//        isbnsNotValid.add("119780596520687");
//        isbnsNotValid.add("0-5961-52068-9");
//        isbnsNotValid.add("11 5122 52068 9");
//        isbnsNotValid.add("ISBN-11 0-596-52068-9");
//        isbnsNotValid.add("ISBN-10- 0-596-52068-9");
//
//        for (String isbn : isbnsNotValid) {
//            assert !(booksService.validateIsbn(isbn));
//        }
//    }
//
//    @Test
//    void ifTheIdToBeDeletedIsNotPresentThanThrowBookNotFoundException() {
//        assertThrows(BookNotFoundException.class, () -> booksService.deleteBookById(0));
//    }
//
//    @Test
//    void deleteBookThrowsIllegalArgumentExceptionWhenIdIsNegativ() {
//        assertThrows(IllegalArgumentException.class, () -> booksService.deleteBookById(-1));
//    }
//
//    @Test
//    void getLendingPeriodsReturnsAllPeriodsByEnteredId() {
//        Book bookDB0 = new Book(0);
//        String date0 = "2020-01-01";
//        String date1 = "2015-03-03";
//        String date2 = "2015-05-05";
//        String date3 = "2020-02-02";
//        String date4 = "2020-04-04";
//        String date5 = "2020-06-06";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateTime0 = LocalDate.parse(date0, formatter);
//        LocalDate dateTime1 = LocalDate.parse(date1, formatter);
//        LocalDate dateTime2 = LocalDate.parse(date2, formatter);
//        LocalDate dateTime3 = LocalDate.parse(date3, formatter);
//        LocalDate dateTime4 = LocalDate.parse(date4, formatter);
//        LocalDate dateTime5 = LocalDate.parse(date5, formatter);
//        LendingPeriod time0 = new LendingPeriod(bookDB0, dateTime0, dateTime3);
//        LendingPeriod time1 = new LendingPeriod(bookDB0, dateTime1, dateTime4);
//        LendingPeriod time2 = new LendingPeriod(bookDB0, dateTime2, dateTime5);
//        List<LendingPeriod> list = List.of(time0, time1, time2);
//
//        when(booksRepository.getLendingPeriodsByBookId(0)).thenReturn(list);
//
//        Collection<LendingPeriod> books = booksService.getLendingPeriodsByBookId(0);
//        assert (books.containsAll(list));
//    }
//
// --------------------Need help!-------------------------------
//    @Test
//    void addNewLendingPeriodReturnsTheEnteredLendingPeriod() {
//        Book bookDBMock = mock(Book.class);
//        BooksRepository booksRepositoryMock = mock(BooksRepository.class);
//        String date0 = "2020-01-01";
//        String date1 = "2020-03-03";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateTime0 = LocalDate.parse(date0, formatter);
//        LocalDate dateTime1 = LocalDate.parse(date1, formatter);
//
//        Book bookDB = new Book(1);
//        bookDB.setIsbn("ISBN-10: 0-596-52068-9");
//        bookDB.setTitle("Testbuch");
//        LendingPeriod lendingPeriod = new LendingPeriod();
//        lendingPeriod.setBook(bookDB);
//        lendingPeriod.setId(1);
//        lendingPeriod.setStartDate(dateTime0);
//        lendingPeriod.setEndDate(dateTime1);
//        List<LendingPeriod> periodList = new ArrayList<>();
//        periodList.add(lendingPeriod);
//
//        booksService.addNewLendingPeriod(1, lendingPeriod);
//        when(booksService.getLendingPeriodsByBookId(1)).equals(lendingPeriod);
//
//        assert (booksRepository.addNewLendingPeriod(1, lendingPeriod).getEndDate()).equals(lendingPeriod.getEndDate());
//        LendingPeriod lendingPeriodMock = mock(LendingPeriod.class);
//
//         Verhalten der Mock Objekte definieren
//        when(lendingPeriodMock.getBookDB()).thenReturn(bookDB);
//        when(lendingPeriodMock.getId()).thenReturn(1);
//        when(lendingPeriodMock.getStartDate()).thenReturn(dateTime0);
//        when(lendingPeriodMock.getEndDate()).thenReturn(dateTime1);
//
//        // Objekt mit deinem Mock erzeugen
//        LendingPeriod lendingPeriodReal = booksService.addNewLendingPeriod(1, lendingPeriod);
//
//    }
//}