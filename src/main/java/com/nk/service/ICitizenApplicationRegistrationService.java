package com.nk.service;

import com.nk.binding.CitizenAppRegistrationInput;
import com.nk.exception.InvalidSSNException;

public interface ICitizenApplicationRegistrationService {
	public Integer registerCitizenApplication(CitizenAppRegistrationInput input) throws InvalidSSNException;
}
