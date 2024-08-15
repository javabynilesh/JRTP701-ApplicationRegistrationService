package com.nk.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nk.binding.CitizenAppRegistrationInput;
import com.nk.service.ICitizenApplicationRegistrationService;

@RestController
@RequestMapping("api/v1/CitizenAR-api")
public class CitizenApplicationRegistrationOperationController {

	@Autowired
	private ICitizenApplicationRegistrationService registrationService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenAppRegistrationInput inputs){
		try {
			//use service
			int appId= registrationService.registerCitizenApplication(inputs);
			if(appId > 0) {
				return new ResponseEntity<>("Citizen Application is registered with the id:"+appId,HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("Invalid SSN or Citizen must belong to california state::",HttpStatus.OK);
			}
		}//try
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}//method
}//class
