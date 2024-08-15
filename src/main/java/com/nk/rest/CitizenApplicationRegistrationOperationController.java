package com.nk.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.binding.CitizenAppRegistrationInput;
import com.nk.exception.InvalidSSNException;
import com.nk.service.ICitizenApplicationRegistrationService;

@RestController
@RequestMapping("api/v1/CitizenAR-api")
public class CitizenApplicationRegistrationOperationController {

	@Autowired
	private ICitizenApplicationRegistrationService registrationService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenAppRegistrationInput inputs) throws InvalidSSNException{
		//use service
		int appId= registrationService.registerCitizenApplication(inputs);
		return new ResponseEntity<>("Citizen Application is registered with the id:"+appId,HttpStatus.CREATED);
		
	}//method
}//class
