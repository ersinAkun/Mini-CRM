package com.crm.exception.message;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiResponseError {
	
	// AMACIM : customize error mesajlatını bu sınıf içinde tutacağız
	private HttpStatus status;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	
	// exception mesajı
	private String message;
	
	// request edilen end-pointi tutmak için
	private String requestURI;
	
	// private constructor
	private ApiResponseError() {
		timestamp = LocalDateTime.now();
	}
	
	public ApiResponseError(HttpStatus status) {
		this();
		this.message="Unexpected Error";
		this.status = status;
	}
	public ApiResponseError(HttpStatus status, String message, String requestURI) {
		this(status); // 1 parametreli olan constructorı çağırıyor
		this.message=message;
		this.requestURI = requestURI;
	}

	// GEtter- Setter
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
