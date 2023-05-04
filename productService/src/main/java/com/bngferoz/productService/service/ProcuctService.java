package com.bngferoz.productService.service;
import com.bngferoz.productService.model.ProductRequest;
import com.bngferoz.productService.model.ProductResponse;


public interface ProcuctService {
    long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);
}
