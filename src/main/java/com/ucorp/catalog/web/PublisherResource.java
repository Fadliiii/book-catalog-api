package com.ucorp.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucorp.catalog.annotaion.LogThiMethod;
import com.ucorp.catalog.dto.PublisherCreateDTO;
import com.ucorp.catalog.dto.PublisherListResponseDTO;
import com.ucorp.catalog.dto.PublisherUpdateDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.exception.BadRequestException;
import com.ucorp.catalog.exception.ResourceNotFoundException;
import com.ucorp.catalog.service.PublisherService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Validated
@AllArgsConstructor
@RestController
@Log4j2
public class PublisherResource {

	private final PublisherService publisherService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateDTO dto) {
		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/v1/publisher")).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> updatePublisher(@PathVariable @Size(max = 36,min = 36,message = "publisher.id.not.uuid") String publisherId,
			@RequestBody @Valid PublisherUpdateDTO dto) {
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")
	@LogThiMethod
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponDTO<PublisherListResponseDTO>>findPublisherList(
			@RequestParam(name="pages",required = true,defaultValue = "0") Integer pages,
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name= "soryBy", required = true,defaultValue = "name") String sortBy,
			@RequestParam(name="direction",required = true,defaultValue = "asc") String direction,
			@RequestParam(name = "publisherName",required = false) String publisherName){
//		if(pages < 0 )throw new ResourceNotFoundException("invalid Pages");
	   log.info(">>> CONTROLLER AUTH = " + SecurityContextHolder.getContext().getAuthentication());

		return ResponseEntity.ok(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}
}
