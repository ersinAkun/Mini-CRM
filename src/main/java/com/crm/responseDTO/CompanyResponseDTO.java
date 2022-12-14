package com.crm.responseDTO;

import java.time.LocalDateTime;
import java.util.Date;
import com.crm.domain.enums.CompanyIndustry;
import com.crm.domain.enums.CompanyStatus;
import com.crm.domain.enums.CompanyType;
import com.crm.domain.enums.CompanyWhereWasFound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CompanyResponseDTO {

    private Long id;
    private String name;

    private String owner;

    private String address;

    private String country;

    private String city;

    private String phoneNumber;

    private String leadWhatsapp;

    private String lastActivity;

    private Date lastActivityDate;

    private String linkedPage;

    private String timeZone;

    private String webPage;

    private String rfq;

    private String whoFind;

    private String about;

    private LocalDateTime firstContactDate;

    private Boolean isMailSent;

    private Boolean isMsgSent;

    private Boolean isOrder;

    private String note;

    private List emails;

    private CompanyStatus companyStatus;

    private CompanyIndustry industry;

    private CompanyWhereWasFound companyWhereWasFound;

    private CompanyType companyType;
}
