package bits.project.bookexchangeplatform.controller;

import bits.project.bookexchangeplatform.dto.BookDto;
import bits.project.bookexchangeplatform.dto.BookList;
import bits.project.bookexchangeplatform.entities.Book;
import bits.project.bookexchangeplatform.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class SearchController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<BookList> searchBooks(
            @RequestBody BookDto bookDto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Book> result = bookService.searchBooks(bookDto, page, size);
        List<Book> books = !result.getContent().isEmpty() ? result.getContent() : new ArrayList<>();
        return ResponseEntity.ok(BookList.builder().books(books).noOfBooks(result.getNumberOfElements()).build());
    }
}
