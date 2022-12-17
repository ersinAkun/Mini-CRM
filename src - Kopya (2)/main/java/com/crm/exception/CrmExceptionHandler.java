package com.crm.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crm.exception.message.ApiResponseError;

@ControllerAdvice // merkezi exception handle etmek için @ControllerAdvice ekledim
public class CrmExceptionHandler  extends ResponseEntityExceptionHandler{
	
	// AMACIM : custom bir exception sistemini kurmak, gelebilecek exceptionları override ederek, istediğim yapıda
	// cevap verilmesini sağlıyorum
	
	Logger logger = LoggerFactory.getLogger(CrmExceptionHandler.class);
	
   private ResponseEntity<Object> buildResponseEntity(  ApiResponseError error) {
	   logger.error(error.getMessage()); //  eception fırlarsa mesajını loggladık
	   return new ResponseEntity<>(error, error.getStatus());
	   
   }
	
	
	@ExceptionHandler(ResourceNotFoundException.class) // bu @ ile custom exceptionrımı yakalayacağım
	protected ResponseEntity<Object> handleResourceNotFoundException ( ResourceNotFoundException ex, WebRequest  request  )   {
																																																// WebRequest = gelen request
			ApiResponseError error =  new ApiResponseError( HttpStatus.NOT_FOUND, 
																														ex.getMessage(), 
																														request.getDescription(false)  );
	        
			/* 
			 *  Map<String,String> map= new HashMap<>();
			 *  map.put("time", LocalDateTime.noe().toString());
		     *  map.put("message", ex.getMessage());
		     *  return new ResponseEntity<>(map,HttpStatus.CREATED);
			 */
				
			
			return buildResponseEntity(error);
			
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		    List<String> errors = ex.getBindingResult().getFieldErrors(). // bütün field errorlarını get ile aldım
		    																			stream().
		    																			map(e->e.getDefaultMessage()).//bütün errorların getMessage() metodunu alıyorum
		    																			collect(Collectors.toList());
		    
		    ApiResponseError error = new  ApiResponseError(HttpStatus.BAD_REQUEST,
		    																				errors.get(0).toString(),
		    																				request.getDescription(false));
		    
		    return buildResponseEntity(error);
		    		
	
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		      ApiResponseError error = new  ApiResponseError(HttpStatus.BAD_REQUEST,
		    		  																					ex.getMessage(),
		    		  																				     request.getDescription(false));
		      return buildResponseEntity(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiResponseError error = new  ApiResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
					ex.getMessage(),
				     request.getDescription(false));
        return buildResponseEntity(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ApiResponseError error = new  ApiResponseError(HttpStatus.BAD_REQUEST,
				ex.getMessage(),
			     request.getDescription(false));
    return buildResponseEntity(error);
		
	}
	
	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {
		ApiResponseError error = new ApiResponseError(HttpStatus.CONFLICT, 
																												ex.getMessage(), 
																												request.getDescription(false));
		return buildResponseEntity(error);
	}
	
	
	// Security ile ilgili Exceptionlar handle adiliyor
	
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		ApiResponseError error = new ApiResponseError(HttpStatus.FORBIDDEN, 
																												ex.getMessage(), 
																												request.getDescription(false));
		return buildResponseEntity(error);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
		ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST, 
																												ex.getMessage(), 
																												request.getDescription(false));
		return buildResponseEntity(error);
	}
	
	
	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
		ApiResponseError error = new ApiResponseError(HttpStatus.BAD_REQUEST, 
																												ex.getMessage(), 
																												request.getDescription(false));
		return buildResponseEntity(error);
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleRuntimeException ( RuntimeException ex, WebRequest  request  )   {
		
		ApiResponseError error =  new ApiResponseError( HttpStatus.INTERNAL_SERVER_ERROR, 
																									ex.getMessage(), 
																									request.getDescription(false)  );

		return buildResponseEntity(error);

}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGeneralException ( Exception ex, WebRequest  request  )   {
		
		ApiResponseError error =  new ApiResponseError( HttpStatus.INTERNAL_SERVER_ERROR, 
																								ex.getMessage(), 
																								request.getDescription(false)  );

		return buildResponseEntity(error);

}
	
}
