package bits.project.bookexchangeplatform.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {
    private Long userId;
    private String token;
}
