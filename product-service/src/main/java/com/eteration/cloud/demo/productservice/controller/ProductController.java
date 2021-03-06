package com.eteration.cloud.demo.productservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eteration.cloud.demo.productservice.config.ProductServiceConfig;
import com.eteration.cloud.demo.productservice.dto.ProductDTO;

@RestController
@RequestMapping("/products")
@RefreshScope
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProductServiceConfig productServiceConfig;
	

	@RequestMapping("/{id}")
	public ProductDTO readProduct(@PathVariable("id") long productId) {
		ProductDTO p = new ProductDTO();
		double  productBasePrice = 50;
		p.setId(productId);
		p.setName("Product" + productId);
		p.setPrice(productBasePrice + (productBasePrice*productServiceConfig.getTaxRatio()/100));
		logger.info("Returning product : {}", productId);

		return p;
	}
	
	@RequestMapping("/conf")
	public ProductServiceConfig conf() {
		

		return productServiceConfig;
	}

}
