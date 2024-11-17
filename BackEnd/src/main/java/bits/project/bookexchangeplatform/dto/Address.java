package bits.project.bookexchangeplatform.dto;

import lombok.Data;

@Data
public class Address {
    private String streetName;
    private String pinCode;
    private String city;
    private String state;
}
