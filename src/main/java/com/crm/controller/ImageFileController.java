package com.crm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.crm.domain.ImageFile;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.ImageFileResponseDTO;
import com.crm.responseDTO.ImageSavedResponse;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.ImageFileService;

@RestController
@RequestMapping("/files")
public class ImageFileController {
	
	@Autowired
	private ImageFileService imageFileService;
	
	// *********** Upload ********************
	@PostMapping("/upload")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')  ")   
	public ResponseEntity<ImageSavedResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		
		  String imageId = imageFileService.saveImage(file);
		  
		 ImageSavedResponse response = new ImageSavedResponse(imageId, ResponseMessage.IMAGE_SAVED_RESPONSE_MESSAGE,true);
		 return ResponseEntity.ok(response);
		
	}
	
	//********************* Download***************
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
		
		      ImageFile imageFile  =   imageFileService.getImageById(id);
		      
		      return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + imageFile.getName()).
		    		  body(imageFile.getImageData().getData());
	}
	
	
	//***********************Image Display*************************
	@GetMapping("/display/{id}")
	public ResponseEntity<byte[]> displayFile(@PathVariable String id) {
		
	      ImageFile imageFile  =   imageFileService.getImageById(id);
	      // header bilgisine MediaType bilgisini giriyorum
	      HttpHeaders header = new HttpHeaders();
	      header.setContentType(MediaType.IMAGE_PNG);
	      
	      return new ResponseEntity<>(imageFile.getImageData().getData(), header, HttpStatus.OK);
	}
	
	//*************************GetAllimage*******************
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')  ")
	public ResponseEntity<List<ImageFileResponseDTO>> getAllImages( ) {
		
		   List<ImageFileResponseDTO> allImageDTO =  imageFileService.getAllImages();
		   
		   return ResponseEntity.ok(allImageDTO);
		
	}
	
	//*****************Delete Image ******************
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')  ")
	public ResponseEntity<CrmResponse> deleteImageFile(@PathVariable String id) {
		imageFileService.removeById(id);
		
		CrmResponse response = new CrmResponse(ResponseMessage.IMAGE_DELETE_RESPONSE_MESSAGE,true);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
