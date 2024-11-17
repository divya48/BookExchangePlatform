package bits.project.bookexchangeplatform.helpers;

import bits.project.bookexchangeplatform.entities.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSpecification {
    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(title) ? criteriaBuilder.like(root.get("title"), "%" + title + "%") : null;
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(author) ? criteriaBuilder.like(root.get("author"), "%" + author + "%") : null;
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(genre) ? criteriaBuilder.equal(root.get("genre"), genre) : null;
    }

    public static Specification<Book> hasCondition(String condition) {
        return (root, query, criteriaBuilder) ->
                StringUtils.hasText(condition) ? criteriaBuilder.equal(root.get("condition"), condition) : null;
    }

    public static Specification<Book> hasAvailabilityStatus(Boolean isAvailable) {
        return (root, query, criteriaBuilder) ->
                isAvailable != null ? criteriaBuilder.equal(root.get("isAvailable"), isAvailable) : null;
    }
}
