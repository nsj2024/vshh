package com.iiht.fc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	Integer productId;
	String name;
	String category;
	String brand;
	double price;
}
