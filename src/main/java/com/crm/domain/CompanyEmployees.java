package com.crm.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.crm.domain.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_companyEmployees")
public class CompanyEmployees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String jobTitle;
	@Column
	private String phoneNumber;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String country;
	@Column
	private String state;
	@Column
	private Boolean hasWhatsapp;
	@Column
	private String notes;
	@Column
	private String speaks;
	@Column
	private Boolean builtIn;

	@Enumerated(EnumType.STRING)
	private Department employeeDepartment;

	@ManyToMany // Company ve emplooye için 3.tablo company'i kim buldu
	@JoinTable(name = "tbl_employee_company", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	List<Company> foundedCompanies = new ArrayList<>();

	@ManyToMany // Roller için 3.tablo
	@JoinTable(name = "t_employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}
