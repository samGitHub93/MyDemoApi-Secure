package org.mydemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUsernamePwdDTO {

    @NotBlank(message = "Field 'Username' cannot be blank.")
    private String dtoUsername;

    @NotBlank@NotBlank(message = "Field 'Password' cannot be blank.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
            message = "Password must have minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character."
    )
    private String dtoPassword;
}
