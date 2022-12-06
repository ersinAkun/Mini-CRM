package com.crm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator="uuid") //12 karakterlik Strink bir id
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;

	
    private String name;

    
    private String type;

    
    private long length;

    @OneToOne(cascade=CascadeType.ALL)// ImageFile silinirse, imageData da silinsin
    private ImageData imageData;

    public ImageFile(String name, String type,ImageData imageData) {
        this.name=name;
        this.type=type;
        this.imageData=imageData;
        this.length=imageData.getData().length; //ImageFile uzuunlugu imageData dan cekiliyor
    }
}
