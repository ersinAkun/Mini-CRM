package com.crm.requestDTO;

import javax.validation.constraints.Email;
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
public class EmailsRequestDTO {

    
    @Size(max = 50)
	@Email
	@NotNull(message = "Please provide email")
    private String email;
   
    
}

