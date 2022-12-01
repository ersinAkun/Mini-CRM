package com.crm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_ImageFile")
public class ImageFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column
	private String name;
	@Column
	private String type;
	@Column
	private Long length;
}
