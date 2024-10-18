package org.mydemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "age")
    @NotNull
    @Min(18)
    private int age;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character."
    )
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "birth_date")
    @NotNull
    @Past
    private Date birthDate;

}
