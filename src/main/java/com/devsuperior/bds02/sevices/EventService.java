package com.devsuperior.bds02.sevices;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.sevices.exceptions.ResourceNotFoundException;


@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public EventDTO uptdate(Long id, EventDTO dto) {
		try {
			Event entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity.setDate(dto.getDate());
			entity.setUrl(dto.getUrl());
			entity.setCity(returnCity(dto.getCityId()));
			return new EventDTO(entity);
		}catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("ID not found " + id);
		}
	}
	
	private City returnCity(Long id) {
		City entity = cityRepository.getOne(id);
		return entity;
		
	}
}
