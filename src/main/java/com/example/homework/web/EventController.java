package com.example.homework.web;

import com.example.homework.dto.EventDTO;
import com.example.homework.entity.Event;
import com.example.homework.facade.EventFacade;
import com.example.homework.payload.response.MessageResponse;
import com.example.homework.repository.EventRepository;
import com.example.homework.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@CrossOrigin
public class EventController {
	@Autowired
	private EventService eventService;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventFacade eventFacade;

	@PostMapping("/create")
	public ResponseEntity<MessageResponse> createEvent(@RequestBody Event event) {
		eventService.createEvent(event);
		return new ResponseEntity<>(new MessageResponse("Event created"), HttpStatus.OK);
	}


	@GetMapping("/all")
	public ResponseEntity<List<EventDTO>> getAllEvents() {

		List<EventDTO> eventDTOList = eventRepository.findAll()
				.stream()
				.map(eventFacade::eventToEventDTO)
				.collect(Collectors.toList());
		return new ResponseEntity<>(eventDTOList, HttpStatus.OK);
	}


}
