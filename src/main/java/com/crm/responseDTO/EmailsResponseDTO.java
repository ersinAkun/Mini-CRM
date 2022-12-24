package com.crm.responseDTO;





import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailsResponseDTO {

	
	private Long id;
	
    private String email;
    
    private Long company_id;
    
    private String company_name;
    
    
    
}
