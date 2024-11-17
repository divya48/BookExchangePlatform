package bits.project.bookexchangeplatform.dto;

import bits.project.bookexchangeplatform.entities.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookList {
    int noOfBooks;
    List<Book> books;
}
