package com.example.Service_A.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FoodOrderEntityA {

	
	@Id
	private int foodOrderId;
	private String foodOrderName;
	private Double foodOrderPrice;
	private int foodOrderQuantity;

}
