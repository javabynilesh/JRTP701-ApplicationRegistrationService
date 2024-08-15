package com.nk.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nk.binding.CitizenAppRegistrationInput;
import com.nk.entity.CitizenAppRegistrationEntity;
import com.nk.exception.InvalidSSNException;
import com.nk.repository.IApplicationRegistrationRepository;

import reactor.core.publisher.Mono;


@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService{

	@Autowired
	private IApplicationRegistrationRepository citizenRepo;
	@Autowired
	private RestTemplate template;
	@Autowired
	private WebClient webclient;
	@Value("${ar.ssa-web.url}")
	private String endPointUrl;
	@Value("${ar.state}")
	private String targetState;
		
	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInput inputs) throws InvalidSSNException {
		//perform webservice call to check weather SSN is valid or not and to get the state name (Using resttemplate)
		//ResponseEntity<String> response	= template.exchange(endPointUrl,HttpMethod.GET,null,String.class,inputs.getSsn());
		//get State name
		//String stateName = response.getBody();
		
		//perform webservice call to check weather SSN is valid or not and to get the state name (Using webclient)
		//String stateName = webclient.get().uri(endPointUrl,inputs.getSsn()).retrieve().bodyToMono(String.class).block();
		
		//perform webservice call to check weather SSN is valid or not and to get the state name (Using webclient)
		//get State name
		 Mono<String> response = webclient.get().uri(endPointUrl,inputs.getSsn()).retrieve()
			.onStatus(HttpStatus.BAD_REQUEST::equals, res->res.bodyToMono(String.class).map(ex->new InvalidSSNException("invalid ssn"))).bodyToMono(String.class);
		
		 String stateName = response.block();
		//register  citizen if he belongs to california state (CA)
		if(stateName.equalsIgnoreCase(targetState)) {
			//prepare the entity Object 
			CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
				BeanUtils.copyProperties(inputs, entity);
				entity.setStateName(stateName);
				//save the object
				int appId = citizenRepo.save(entity).getAppId();
				return appId;
		}
		
		throw new InvalidSSNException("Invalid SSN no.");
	}

}
