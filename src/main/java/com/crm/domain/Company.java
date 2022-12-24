package com.crm.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;//unique olan kısım name

    @Column(length = 30, nullable = false)
    private String owner;

    @Column(length = 90, nullable = false)
    private String address;

    @Column(length = 30, nullable = false)
    private String country;

    @Column(length = 30, nullable = false)
    private String city;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column(length = 14)
    private String leadWhatsapp;

    @Column(length = 90)
    private String lastActivity;

    @Column(nullable = false)
    private Date lastActivityDate;

    @Column(length = 30)
    private String linkedPage;

    @Column(length = 10, nullable = false)
    private String timeZone;

    @Column(length = 30)
    private String webPage;

    @Column(length = 150, nullable = false)
    private String RFQ;

    @Column
    private Long whoFind;

    @Column(length = 250)
    private String about;

    @Column(nullable = false)
    private LocalDateTime firstContactDate;

    @Column(length = 10, nullable = false)
    private Boolean isMailSent;

    @Column(length = 10, nullable = false)
    private Boolean isMsgSent;

    @Column(length = 10, nullable = false)
    private Boolean isOrder;

    @Column(length = 255)
    private String note;

    @OneToOne(fetch = FetchType.LAZY)
    private Lead lead;


    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;


    @Enumerated(EnumType.STRING)
    private CompanyIndustry industry;


    @Enumerated(EnumType.STRING)
    private CompanyWhereWasFound companyWhereWasFound;


    @Enumerated(EnumType.STRING)
    private CompanyType companyType;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emails> emails;


}