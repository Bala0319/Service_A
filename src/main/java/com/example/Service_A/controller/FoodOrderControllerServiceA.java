package com.example.Service_A.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Service_A.ServiceInter.FoodOrderServiceAServiceInterface;
import com.example.Service_A.dto.FoodOrderDtoA;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FoodOrderControllerServiceA {
	
	@Autowired
	FoodOrderServiceAServiceInterface foodOrderServiceAServiceInterface;
	
	
	@PostMapping("/saveFoodOrderA")
	public FoodOrderDtoA saveFoodOrderA(@RequestBody FoodOrderDtoA foodOrderDtoA) {
		
        log.info("[SaveFoodOrderA Method] - in Service_AController with Food Order Dto - {}", foodOrderDtoA);
        
		FoodOrderDtoA savedFoodOrderDtoA = foodOrderServiceAServiceInterface.saveFoodOrderA(foodOrderDtoA);
		
		log.info("Food Order saved successfully: {}", savedFoodOrderDtoA);

		
        return foodOrderDtoA;
	}

}
