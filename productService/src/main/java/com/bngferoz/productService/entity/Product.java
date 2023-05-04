package com.bngferoz.productService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    
    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private long price;
    
    @Column(name = "quantity")
    private long quantity;
}
