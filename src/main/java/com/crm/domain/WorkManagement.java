package com.crm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class WorkManagement {

	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	  
	  private String title;
	  
	  private Date startDate;
	  
	  private Date expectedEndDate;
	  
	  private String assignee;
	  
	  private String reporter;
	  
	  private String  comments;
	  
	  private  Date createDate;
	  
	  private  Date updateDate;
	  
	  private  Date finishedDate;
	  
	  private String description;
	  
	  @Enumerated
	  private WMStatus status;
	  
	  @Enumerated
	  private WMCategory category;
	  
	  @Enumerated
	  private WMPriority priority;
	  
}
