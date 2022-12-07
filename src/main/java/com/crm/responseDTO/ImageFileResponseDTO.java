package com.crm.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageFileResponseDTO {
	
	private String name ;
	private String url;
	private String type;
	private long size; // length !!!

}
