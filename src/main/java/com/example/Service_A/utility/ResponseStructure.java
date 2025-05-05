package com.example.Service_A.utility;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ComponentScan(basePackages = "Service_A")
@Data
public class ResponseStructure {
	
	int status;
	String message;

}
