package com.crm.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "tbl_workManagement")
public class WorkManagement {

	//bunu admin yapabilsin
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	  
	  @Column(length = 50, nullable = false)
	  private String title;
	  
	  @Column(nullable = true)
	  private LocalDate startDate;//işe başlama tarihi... . 
	  
	  @Column( nullable = true)
	  private LocalDate expectedEndDate;
	  	 	 
	  @Column(length = 250)
	  private String comments;
	  
	  @Column
	  private  LocalDate createDate;//o günün tarihi atılabilir otomatik
	  
	  @Column( nullable = true)
	  private  LocalDate updateDate;
	  
	  @Column( nullable = true)
	  private  LocalDate finishedDate;
	  
	  @Column(length = 250, nullable = false)
	  private String description;//işin tarifi... fabrika ziyareti yapılacak
	  	  
	  @ManyToOne
	  private CompanyEmployees assignee;//path ile göndereceğiz
	  	  
	  @Enumerated(EnumType.STRING)
	  private WMStatus status;
	  
	  @Enumerated(EnumType.STRING)
	  private WMCategory category;
	  
	  @Enumerated(EnumType.STRING)
	  private WMPriority priority;
	  
}
