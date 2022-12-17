package com.crm.requestDTO;

import javax.validation.constraints.Email;
<<<<<<< HEAD
=======
import javax.validation.constraints.NotNull;
>>>>>>> 3d5683f27bebbf96b1595e331736c4092b838f76
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
<<<<<<< HEAD
	//@NotNull(message = "Please provide email")
    private String email;
    
}
=======
	@NotNull(message = "Please provide email")
    private String email;
   
    
}

>>>>>>> 3d5683f27bebbf96b1595e331736c4092b838f76
