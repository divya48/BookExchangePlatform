package bits.project.bookexchangeplatform.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String condition;
    private boolean isAvailable;
}
