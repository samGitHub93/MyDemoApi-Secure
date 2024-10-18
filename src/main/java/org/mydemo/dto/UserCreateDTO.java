package org.mydemo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserCreateDTO {

    @NotBlank(message = "Field 'Name' cannot be blank.")
    private String dtoName;

    @NotNull(message = "Field 'Age' cannot be blank.")
    @Min(value = 18, message = "'Age' must be at least 18.")
    private int dtoAge;

    @Email(message = "'E-Mail' is probably wrong.")
    private String dtoEmail;

    @NotBlank(message = "Field 'Password' cannot be blank.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character."
    )
    private String dtoPassword;

    private String dtoUsername;

    @NotNull(message = "Field 'Birth Date' cannot be blank.")
    @Past(message = "'Birth Date' must be in the past.")
    private Date dtoBirthDate;
}
