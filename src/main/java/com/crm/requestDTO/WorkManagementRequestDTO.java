package com.crm.requestDTO;

import java.time.LocalDate;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.crm.domain.enums.WMCategory;
import com.crm.domain.enums.WMPriority;
import com.crm.domain.enums.WMStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkManagementRequestDTO {

	  
	  @Size(max = 250)
	  private String title;
	  
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	  @NotNull(message = "Please provide Job Start Date")
	  private LocalDate startDate;
	  
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	  @NotNull(message = "Please provide expected completion date of job")
	  private LocalDate expectedEndDate;
	  
//	  @Size(max = 50)
//      @NotNull(message = "Please provide assignee")
//	  private String assignee;
	  
	  
	  @Size(max = 250)
	 // @NotBlank(message = "Comment cannot be empty")
	  private String  comments;
	  
	
	  
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	  //@NotNull(message = "Please provide update date of job")
	  private  LocalDate updateDate;
	  
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	 // @NotNull(message = "Please provide Finished date of job")
	  private  LocalDate finishedDate;
	  
	  @Size(max = 250)
	  @NotNull(message = "Please provide detailed description")
	  private String description;
	  
	  @Enumerated
	  private WMStatus status;
	  
	  @Enumerated
	  private WMCategory category;
	  
	  @Enumerated
	  private WMPriority priority;
}
