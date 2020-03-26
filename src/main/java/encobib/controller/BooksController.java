package encobib.controller;

import java.util.List;
import java.util.Optional;

import encobib.exceptions.BookNotFoundException;
import encobib.model.Book;
import encobib.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBooksInSlack() {
        return booksService.getAllBooks();
    }

    @RequestMapping
    public String getAllBooks(Model model) {
        List<Book> list = booksService.getAllBooks();
        model.addAttribute("books", list);
        return "index";
    }

    @RequestMapping(path = { "/edit", "/edit/{id}" })
    public String editBookById(Model model, @PathVariable("id") Optional<Integer> id)
        throws BookNotFoundException {
        if (id.isPresent()) {
            Book entity = booksService.getBookById(id.get());
            model.addAttribute("book", entity);
        } else {
            model.addAttribute("book", new Book());
        }
        return "add-edit-book";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteBookById(Model model, @PathVariable("id") int id) throws BookNotFoundException {
        try {
            booksService.deleteBookById(id);
            //            return getBookById(id).getTitle() + " with ID " + id + " has been deleted successfully.";
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Book book) {
        booksService.createOrUpdateBook(book);
        return "redirect:/";
    }

    //    @GetMapping(value = "/{id}")
    //    Book getBookById(@PathVariable int id) {
    //        try {
    //            return booksService.getBookById(id);
    //        } catch (IllegalArgumentException iae) {
    //            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
    //        }
    //    }

    //
    //    @GetMapping(value = "/{isbn}/lendingperiods")
    //    List<LendingPeriod> getLendingPeriodsByBookId(@PathVariable int id) {
    //        try {
    //            return booksService.getLendingPeriodsByBookId(id);
    //        } catch (IllegalArgumentException iae) {
    //            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
    //        }
    //    }
    //
    //    @PostMapping(value = "/{isbn}/lendingperiods")
    //    LendingPeriod addNewLendingPeriod(@PathVariable int id, @RequestBody LendingPeriod lendingPeriod) {
    //        try {
    //            return booksService.addNewLendingPeriod(id, lendingPeriod);
    //        } catch (IllegalArgumentException iae) {
    //            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
    //        }
    //    }
}
