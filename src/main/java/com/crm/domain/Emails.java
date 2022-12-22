package com.crm.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_email")
public class Emails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

   //@Column(length = 500, nullable = false,unique = true)
   //@Email
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;





//    @ManyToOne
//    @JoinColumn(name="company_id")
//    private Long company_id;
//
//


}