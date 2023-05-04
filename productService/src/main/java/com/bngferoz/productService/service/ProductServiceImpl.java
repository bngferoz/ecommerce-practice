package com.bngferoz.productService.service;

import com.bngferoz.productService.entity.Product;
import com.bngferoz.productService.repo.ProductRepo;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bngferoz.productService.model.ProductRequest;
import com.bngferoz.productService.model.ProductResponse;
import com.bngferoz.productService.exception.ProductServiceCustomException;

@Service
@Log4j2
public class ProductServiceImpl implements ProcuctService{

    @Autowired
    private ProductRepo productRepo;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product");
        Product product = new Product();
        product.setProductName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setPrice(productRequest.getPrice());
        productRepo.save(product);
        return product.getProductId();
    }
	@Override
	public ProductResponse getProductById(long productId) {
		log.info("Get Product by product id: {}",productId);
		Product product = new Product();
		product = productRepo.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product with given Id is not found", "PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse = new ProductResponse();
		
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
		 
	}
	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Product with Id: {} will be reduced by quantity {}", productId, quantity);
		Product product = productRepo.findById(productId)
				.orElseThrow(()->new ProductServiceCustomException("Product with given Id not found", "PRODUCT_NOT_FOUND"));
		
		//check insufficient quantity
		if(product.getQuantity()<quantity) {
			throw new ProductServiceCustomException("Product does not have sufficient quantity","INSUFFICIENT_QUANTITY");
		}
		
		product.setQuantity(product.getQuantity()-quantity);
		productRepo.save(product);
		log.info("Product Quantity updated successfully!");
	}
}
