package com.crm.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.domain.enums.CompanyWhereWasFound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
	
  @Column
  private String name;
  
  @Column
  private String owner;
  
  @Column
  private String address;
  
  @Column
  private String country;
  
  @Column
  private String city;
  
  @Column
  private String phone;
  
  @Column
  private String leadWhatsapp;
 
  @Column
  private String lastActivity;
  
  @Column
  private Date lastActivityDate;
  
  @Column
  private String linkedPage;
  
  @Column
  private String timeZone;
  
  @Column
  private String webPage;
  
  @Column
  private String RFQ;
  
  @Column
  private Long whoFind;//path ile gönderebiliriz.
  @Column
  private String whoContacted;//yereldeki şirket sahibi olabilir.body ile gönderelim
  @Column
  private String about;
  @Column
  private Date firstContactDate;
  @Column
  private Boolean isMailSent;
  @Column
  private Boolean isMsgSent;
  @Column 
  private Boolean isOrder;
  @Column
  private String note;
 // @Column
 // private Set<Emails> emails;
  
  @OneToOne(fetch = FetchType.LAZY)
  private Lead lead;
  
  @OneToMany
  private List<Orders> orders;
  
  @OneToMany
  private List<Emails> emails; //???
  
  @Enumerated(EnumType.STRING)
  private CompanyStatus companyStatus;
 
 
  @Enumerated(EnumType.STRING)
 private CompanyIndustry industry;
 
 
  @Enumerated(EnumType.STRING)
 private CompanyWhereWasFound companyWhereWasFound;
 
 
  @Enumerated(EnumType.STRING)
  private CompanyType companyType;
  
}
