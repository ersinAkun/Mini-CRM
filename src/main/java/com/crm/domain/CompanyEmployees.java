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
	@Column(length = 30, nullable = false)
	private String firstName;
	@Column(length = 30, nullable = false)
	private String lastName;
	@Column(length = 50, nullable = false)
	private String email;
	@Column(length = 50, nullable = false)
	private String jobTitle;
	@Column(length = 14, nullable = false)
	private String phoneNumber;
	@Column(length = 30, nullable = false)
	private String address;
	@Column(length = 30, nullable = false)
	private String city;
	@Column(length = 30, nullable = false)
	private String country;
	@Column(length = 30, nullable = false)
	private String state;
	@Column
	private Boolean hasWhatsapp;
	@Column
	private String notes;
	@Column(length = 30)
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
