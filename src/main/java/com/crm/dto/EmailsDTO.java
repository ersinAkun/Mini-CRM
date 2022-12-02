package com.crm.dto;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmailsDTO {

       
    @NotEmpty(message = "Email cannot be left blank.")
    @Email
    @Size(min = 5, max = 50,  message = "Email must be between 5 and 50 characters")
    @NotNull(message = "Please enter email")  
    private String email;
}


