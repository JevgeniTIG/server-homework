package com.example.homework.facade;

import com.example.homework.dto.EventDTO;
import com.example.homework.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventFacade {
	public EventDTO eventToEventDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setId(event.getId());
		eventDTO.setBasicInfo(event.getBasicInfo());
		eventDTO.setLocation(event.getLocation());
		eventDTO.setTimeOfEvent(event.getTimeOfEvent());
		return eventDTO;
	}
}


