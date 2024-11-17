package bits.project.bookexchangeplatform.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
}
