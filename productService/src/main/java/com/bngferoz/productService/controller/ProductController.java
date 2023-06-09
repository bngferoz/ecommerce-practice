package com.bngferoz.productService.controller;

import com.bngferoz.productService.service.ProcuctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bngferoz.productService.model.ProductRequest;
import com.bngferoz.productService.model.ProductResponse;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProcuctService productService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long productId){
    	ProductResponse productResponse 
    		= productService.getProductById(productId);
    	return new ResponseEntity<>(productResponse ,HttpStatus.OK);
    }
    

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @PutMapping("/{id}")
    public ResponseEntity<String> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){
    	productService.reduceQuantity(productId, quantity);
    	return new ResponseEntity<>("Quantity Reduced Successfully!",HttpStatus.OK);
    }
}
