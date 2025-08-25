package com.ucorp.catalog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ucorp.catalog.domain.Publisher;
import com.ucorp.catalog.dto.PublisherCreateDTO;
import com.ucorp.catalog.dto.PublisherListResponseDTO;
import com.ucorp.catalog.dto.PublisherResponseDTO;
import com.ucorp.catalog.dto.PublisherUpdateDTO;
import com.ucorp.catalog.dto.ResultPageResponDTO;
import com.ucorp.catalog.exception.BadRequestException;
import com.ucorp.catalog.repository.PublisherRepository;
import com.ucorp.catalog.service.PublisherService;
import com.ucorp.catalog.util.PaginationUtil;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	public void createPublisher(PublisherCreateDTO dtos) {
		Publisher publisher = new Publisher();
		publisher.setName(dtos.getPublisherName());
		publisher.setCompanyName(dtos.getCompanyName());
		publisher.setAddress(dtos.getAddress());

		publisherRepository.save(publisher);

	}

	@Override
	public void updatePublisher(String publisherId, PublisherUpdateDTO dtos) {
		Publisher publisher = publisherRepository.findBySecureId(publisherId)
				.orElseThrow(() -> new BadRequestException("Invalid publisher Id"));
		publisher.setName(dtos.getPublisherName() == null || dtos.getPublisherName().isBlank() ? publisher.getName()
				: dtos.getPublisherName());
		publisher.setCompanyName(
				dtos.getCompanyName() == null || dtos.getCompanyName().isBlank() ? publisher.getCompanyName()
						: dtos.getCompanyName());
		publisher.setAddress(dtos.getAddress());

		publisherRepository.save(publisher);
	}

	@Override
	public ResultPageResponDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy,
			String direction, String publisherName) {
		publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName + "%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);

		Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);

		List<PublisherListResponseDTO> dtos = pageResult.stream().map((p) -> {
			PublisherListResponseDTO dto = new PublisherListResponseDTO();
			dto.setPublisherId(p.getSecureId());
			dto.setPublisherName(p.getName());
			dto.setCompanyName(p.getCompanyName());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	@Override
	public Publisher findPublisher(String publisherId) {
		return publisherRepository.findBySecureId(publisherId)
				.orElseThrow(() -> new BadRequestException("publisher cant empty"));
	}

	@Override
	public PublisherResponseDTO contructDTO(Publisher publisher) {
		PublisherResponseDTO dto = new PublisherResponseDTO();
		dto.setPublisherId(publisher.getSecureId());
		dto.setPublisherName(publisher.getName());
		return dto;
	}

}
