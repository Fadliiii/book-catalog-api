package com.ucorp.catalog.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.ucorp.catalog.dto.HelloMessageDto;
import com.ucorp.catalog.service.GreetingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloResource {
	private final GreetingService greetingService;

	@GetMapping("/hello")
	public ResponseEntity<HelloMessageDto> helloWorld() {
		log.error("This is error message");
		log.warn("This is warn Message");
		log.info("This is info Message");
		log.debug("This is Debug Message");
		log.trace("This is Trace Message");
		HelloMessageDto helloMessageDto = new HelloMessageDto();
		helloMessageDto.setMessage(greetingService.sayGreeting());
		return ResponseEntity.accepted().body(helloMessageDto);
	}	
	
}
