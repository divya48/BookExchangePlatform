package bits.project.bookexchangeplatform.controller;

import bits.project.bookexchangeplatform.entities.Book;
import bits.project.bookexchangeplatform.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BooksController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<List<Book>> addBook(@RequestParam Long userId, @RequestBody List<Book> books) {
        try {
            List<Book> newBooks = bookService.addBook(userId, books);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBooks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestParam Long bookId, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(bookId, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/removeBook")
    public ResponseEntity<String> deleteBook(@RequestParam Long userId, @RequestBody List<Long> bookIds) {
        try {
            bookService.deleteBook(userId, bookIds);
            return ResponseEntity.ok("Deleted the books successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Book>> getUserBooks(@RequestParam Long userId) {
        List<Book> books = bookService.getUserBooks(userId);
        return ResponseEntity.ok(books);
    }
}
