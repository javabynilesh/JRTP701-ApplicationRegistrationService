package com.nk.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nk.binding.CitizenAppRegistrationInput;
import com.nk.entity.CitizenAppRegistrationEntity;
import com.nk.repository.IApplicationRegistrationRepository;


@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService{

	@Autowired
	private IApplicationRegistrationRepository citizenRepo;
	@Autowired
	private RestTemplate template;
	@Value("${ar.ssa-web.url}")
	private String endPointUrl;
	@Value("${ar.state}")
	private String targetState;
		
	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInput inputs) {
		//perform webservice call to check weather SSN is valid or not and to get the state name
		ResponseEntity<String> response	= template.exchange(endPointUrl,HttpMethod.GET,null,String.class,inputs.getSsn());
		//get State name
		String stateName = response.getBody();
		
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
		return 0;
	}

}
