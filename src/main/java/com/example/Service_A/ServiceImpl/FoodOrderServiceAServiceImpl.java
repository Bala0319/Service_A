package com.example.Service_A.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.example.Service_A.ServiceInter.FoodOrderServiceAServiceInterface;
import com.example.Service_A.dto.FoodOrderDtoA;
import com.example.Service_A.entity.FoodOrderEntityA;
import com.example.Service_A.repository.FoodOrderRespositoryServiceA;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodOrderServiceAServiceImpl implements FoodOrderServiceAServiceInterface {
	
	
	@Autowired
	FoodOrderRespositoryServiceA foodOrderRespositoryServiceA;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public FoodOrderDtoA saveFoodOrderA(FoodOrderDtoA foodOrderDtoA) {
		
        log.info("Starting Food Order save operation for: {}", foodOrderDtoA);

		foodOrderDtoA =  convertEntityToDto(foodOrderRespositoryServiceA.save(convertDtoToEntity(foodOrderDtoA)));
		
        log.info("Food Order entity saved to database: {}", foodOrderDtoA);

		try {
			String Key=String.valueOf(foodOrderDtoA.getFoodOrderId());
			
			kafkaTemplate.send("food-order-service",Key,objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foodOrderDtoA));
			
            log.info("Published Food Order to Kafka with key {}: {}", Key, foodOrderDtoA);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
        log.info("Completed Food Order save operation for: {}", foodOrderDtoA);

		return foodOrderDtoA;
	}
	
	public FoodOrderDtoA convertEntityToDto(FoodOrderEntityA foodOrderEntity) {
		return modelMapper.map(foodOrderEntity, FoodOrderDtoA.class);
	}
	public FoodOrderEntityA convertDtoToEntity(FoodOrderDtoA foodOrderDto) {
		return modelMapper.map(foodOrderDto, FoodOrderEntityA.class);
	}


}
