package com.example.homework.service;

import com.example.homework.entity.Event;
import com.example.homework.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {

	public static final Logger LOG = LoggerFactory.getLogger(EventService.class);

	private final EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}


	public Event createEvent(Event event) {
		Event newEvent = new Event();
		newEvent.setBasicInfo(event.getBasicInfo());
		newEvent.setLocation(event.getLocation());
		newEvent.setTimeOfEvent(LocalDateTime.parse(event.getTimeOfEvent().toString()));
		LOG.info("Saving new event");

		return eventRepository.save(newEvent);
	}


}
