package com.example.tiemchungbe.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SignUpRequest {
    private Integer id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String guardian;
    private String address;
    private String phone;
    @NotNull
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "wrong format, should be abc@abc.com ")
    private String email;
    @NotBlank
    @Length(min = 5,max = 30)
    @JsonIgnore
    private String password;
}
