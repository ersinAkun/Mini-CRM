package com.crm.responseDTO;

import java.util.Date;
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
public class WorkManagemenetResponseDTO {

	  private String title;
	  
	  private Date startDate;

	  private Date expectedEndDate;

	  private String assignee;

	  private String reporter;

	  private String  comments;
	  
	  private  Date updateDate;
	  
	  private  Date finishedDate;

	  private String description;

	  private WMStatus status;

	  private WMCategory category;

	  private WMPriority priority;
}