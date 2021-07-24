package com.example.homework.web;

import com.example.homework.dto.TicketDTO;
import com.example.homework.facade.TicketFacade;
import com.example.homework.payload.request.TicketLoadRequest;
import com.example.homework.payload.response.MessageResponse;
import com.example.homework.repository.EventRepository;
import com.example.homework.repository.TicketRepository;
import com.example.homework.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin
public class TicketController {
	@Autowired
	private TicketService ticketService;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private TicketFacade ticketFacade;


	@PostMapping("/load")
	public ResponseEntity<MessageResponse> loadTickets(@RequestBody List<TicketLoadRequest> requests) {
		ticketService.loadTickets(requests);
		return new ResponseEntity<>(new MessageResponse("Ticket(s) loaded"), HttpStatus.OK);
	}

	@PutMapping("{id}/validate")
	public ResponseEntity<MessageResponse> validateTicket(@PathVariable("id") Long id) {
		if (ticketRepository.existsById(id)) {
			if (ticketService.validateTicket(id)) {
				return new ResponseEntity<>(new MessageResponse("Ticket validated"), HttpStatus.OK);
			} else if (!ticketService.validateTicket(id)) {
				return new ResponseEntity<>(new MessageResponse("Ticket not valid"), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(new MessageResponse("Ticket with id " + id + " does not exist"), HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{id}/all")
	public ResponseEntity<List<TicketDTO>> getAllTicketsForEvent(@PathVariable("id") Long eventId) {
		if (eventRepository.existsById(eventId)) {
			List<TicketDTO> ticketDTOS = ticketService.getAllTicketsForEvent(eventId)
					.stream()
					.map(ticketFacade::ticketToTicketDTO)
					.collect(Collectors.toList());
			return new ResponseEntity<>(ticketDTOS, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}
}
