package com.crm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	private String name;
	
	private String type;
	
	
	private Long length;
}
