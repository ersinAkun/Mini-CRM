package com.crm.requestDTO;


import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "tbl_email")
public class EmailsRequestDTO {

    
    @Size(max = 50)
	@Email
	@NotNull(message = "Please provide email")
    private String email;
    
}
