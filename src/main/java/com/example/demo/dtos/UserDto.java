package com.example.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserDto {
    @NotNull
    @Size(min = 2, max = 30)
    private String username;

    @NotNull
    private String email;

    @NotBlank
    private String password;
}
