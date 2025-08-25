package com.ucorp.catalog.service;

import com.ucorp.catalog.domain.Publisher;
import com.ucorp.catalog.dto.PublisherCreateDTO;
import com.ucorp.catalog.dto.PublisherListResponseDTO;
import com.ucorp.catalog.dto.PublisherResponseDTO;
import com.ucorp.catalog.dto.PublisherUpdateDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;

public interface PublisherService {

	public void createPublisher(PublisherCreateDTO dtos);

	public void updatePublisher(String publisherId, PublisherUpdateDTO dtos);

	// pages: halaman keberapa
	// limit : berapa elemen 1 halaman
	// sortBy : ingin di urutkan berdasarakan kolom yang mana
	// direction : asc atau desc
	public ResultPageResponDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy,
			String direction, String publisherName);
	
	public Publisher findPublisher(String publisherId);
	
	public PublisherResponseDTO contructDTO(Publisher publisher);
}
