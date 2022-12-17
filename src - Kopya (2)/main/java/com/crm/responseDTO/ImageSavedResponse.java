package com.crm.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageSavedResponse extends CrmResponse {
	

	private String imageId;

	public ImageSavedResponse( String imageId, String message, boolean success) {
		super(message,success);
		this.imageId= imageId;
		
	}

}
