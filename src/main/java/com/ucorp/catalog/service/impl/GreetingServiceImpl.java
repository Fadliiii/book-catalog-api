package com.ucorp.catalog.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ucorp.catalog.config.ApplicationProperties;
import com.ucorp.catalog.service.GreetingService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class GreetingServiceImpl implements GreetingService{

	private ApplicationProperties applicationProperties;

	@Override
	public String sayGreeting() {
		return applicationProperties.getWelcomeText()+" Your Time Zone = "+applicationProperties.getTimeZone()+" Your curenncy = "+applicationProperties.getCurrency();
	}

	
}
