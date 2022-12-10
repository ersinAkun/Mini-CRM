package com.crm.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.crm.domain.OrderedProducts;
import com.crm.responseDTO.OrderedProductsResponseDTO;


@Mapper(componentModel = "spring")
public interface OrderedProductsMapper {

	
	List<OrderedProductsResponseDTO> pojoListToResponseList(List<OrderedProducts> orderedProducts);
	
}
