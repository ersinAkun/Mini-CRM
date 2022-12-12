package com.crm.responseDTO;

import java.time.LocalDate;
import com.crm.domain.enums.WMCategory;
import com.crm.domain.enums.WMPriority;
import com.crm.domain.enums.WMStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkManagementResponseDTO {

	  private String title;
	  
	  private LocalDate startDate;

	  private LocalDate expectedEndDate;

	  private String assigneeName;

	 // private String reporter; bunu nasıl kontrol edelim

	  private String  comments;
	  
	  private  LocalDate updateDate;
	  
	  private  LocalDate finishedDate;

	  private String description;

	  private WMStatus status;

	  private WMCategory category;

	  private WMPriority priority;
}