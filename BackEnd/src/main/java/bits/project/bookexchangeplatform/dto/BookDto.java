package bits.project.bookexchangeplatform.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private String title;
    private String author;
    private String genre;
    private String condition;
    private Boolean isAvailable;
}
