package com.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ImageFileRequestDTO {
	
	private String name ;//dosyanın adı geleceği için valid koyulmadı
	
	private String url;//postman tarafından url oluşturulup gönderilecek
	
	private String type;//herhangi bir dosya tipi alınabilir.
	
	private long size;//bunu yml dosyasında max 2mb olacak şekilde belirtmiştik. 
}
