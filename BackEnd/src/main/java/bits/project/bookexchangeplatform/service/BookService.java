package bits.project.bookexchangeplatform.service;
import bits.project.bookexchangeplatform.database.UserBookRepository;
import bits.project.bookexchangeplatform.dto.BookDto;
import bits.project.bookexchangeplatform.entities.Book;
import bits.project.bookexchangeplatform.entities.User;
import bits.project.bookexchangeplatform.database.BookRepository;
import bits.project.bookexchangeplatform.database.UserRepository;
import bits.project.bookexchangeplatform.entities.UserBook;
import bits.project.bookexchangeplatform.helpers.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBookRepository userBookRepository;

    public List<Book> addBook(Long userId, List<Book> books) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        for (Book book : books) {
            bookRepository.save(book);
            UserBook userBook = new UserBook();
            userBook.setUser(user);
            userBook.setBook(book);
            userBook.setStatus("Available");
            userBookRepository.save(userBook);
        }
        return books;
    }

    public Book updateBook(Long bookId, Book bookDetails) throws Exception {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));
        if(!StringUtils.isEmpty(bookDetails.getTitle())){
            book.setTitle(bookDetails.getTitle());
        }
        if(!StringUtils.isEmpty(bookDetails.getAuthor())){
            book.setAuthor(bookDetails.getAuthor());
        }
        if(!StringUtils.isEmpty(bookDetails.getGenre())){
            book.setGenre(bookDetails.getGenre());
        }
        if(!StringUtils.isEmpty(bookDetails.getCondition())){
            book.setCondition(bookDetails.getCondition());
        }
        if(!StringUtils.isEmpty(bookDetails.isAvailable())){
            book.setAvailable(bookDetails.isAvailable());
        }
        return bookRepository.save(book);
    }

    public void deleteBook(Long userId, List<Long> bookId) throws Exception {
        try{
            for(Long id: bookId){
                List<UserBook> userBooks = userBookRepository.findByUserIdAndBookId(userId, id);
                if (userBooks.isEmpty()) {
                    throw new Exception("No such book associated with this user");
                }
                userBookRepository.deleteByUserIdAndBookId(userId, id);
                if (userBookRepository.findByBookId(id).isEmpty()) {
                    // If no UserBook references this book, delete the book itself
                    bookRepository.deleteById(id);
                }
            }
        }catch (Exception e){
            throw new Exception("Error while deleting books");
        }
    }

    public List<Book> getUserBooks(Long userId) {
        List<UserBook> userBooks = userBookRepository.findByUserId(userId);
        return userBooks.stream().map(userBook -> userBook.getBook()).collect(Collectors.toList());
    }

    public Page<Book> searchBooks(BookDto bookDto, int page, int size) {
        Specification<Book> specs = Specification.where(BookSpecification.hasTitle(bookDto.getTitle()))
                .and(BookSpecification.hasAuthor(bookDto.getAuthor()))
                .and(BookSpecification.hasGenre(bookDto.getGenre()))
                .and(BookSpecification.hasAvailabilityStatus(bookDto.getIsAvailable()))
                .and(BookSpecification.hasCondition(bookDto.getCondition()));

        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(specs, pageable);
    }
}
