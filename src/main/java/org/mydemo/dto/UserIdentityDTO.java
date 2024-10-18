package org.mydemo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserIdentityDTO {

    @NotBlank(message = "Field 'Name' cannot be blank.")
    private String dtoName;

    @NotNull(message = "Field 'Age' cannot be blank.")
    @Min(value = 18, message = "'Age' must be at least 18.")
    private String dtoAge;

    @NotNull(message = "Field 'Birth Date' cannot be blank.")
    @Past(message = "'Birth Date' must be in the past.")
    private Date dtoBirthDate;
}
