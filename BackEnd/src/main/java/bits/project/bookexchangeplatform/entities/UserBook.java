package bits.project.bookexchangeplatform.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "userbook")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Reference to the user

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;  // Reference to the book

    // Additional fields can be added to track the status of the book for a user
    private String status;
}
