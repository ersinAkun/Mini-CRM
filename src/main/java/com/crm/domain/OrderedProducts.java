package com.crm.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_Ordered_Products")
public class OrderedProducts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
	
	  @Column(length = 30, nullable = false)
	private String productCode;
	  
	  @Column(length = 30, nullable = false)
	private String productName;
	  
	  @Column(length = 50)
	private String size;
	  
	  @Column(length = 30)
	private Double weight;
	  
	  @Column(length = 30, nullable = false)
	private Double purchasePrice;
	  
	  @Column(length = 30, nullable = false)
	private Double salePrice;
	
	  @Column(length = 30)//ön tarafta gözükmesin ama db'de olsun.. @JsonIGnore olabilir
	private Double netProfit;
	
	

	  
	  @OneToMany(orphanRemoval = true)//orphanRemoval = true...hata alırsam sil
		@JoinColumn(name = "orderedProduct_id")
		private Set<ImageFile> image;
	  
	  @JsonIgnore//stackoverflow olmaması için.
	  @ManyToOne()
	  @JoinColumn(name="supplier_id")
	  private Supplier supplier;
	 

}
