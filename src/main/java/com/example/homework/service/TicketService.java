package com.example.homework.service;

import com.example.homework.entity.Event;
import com.example.homework.entity.Ticket;
import com.example.homework.entity.enums.TicketStatus;
import com.example.homework.facade.TicketFacade;
import com.example.homework.payload.request.TicketLoadRequest;
import com.example.homework.repository.EventRepository;
import com.example.homework.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
	public static final Logger LOG = LoggerFactory.getLogger(TicketService.class);

	private final TicketRepository ticketRepository;
	private final EventRepository eventRepository;
	private final TicketFacade ticketFacade;

	public TicketService(TicketRepository ticketRepository,
						 EventRepository eventRepository,
						 TicketFacade ticketFacade) {
		this.ticketRepository = ticketRepository;
		this.eventRepository = eventRepository;
		this.ticketFacade = ticketFacade;
	}


	public List<Ticket> loadTickets(List<TicketLoadRequest> requests) {
		List<Ticket> newTickets = new ArrayList<>();
		requests.forEach(ticket -> {
			if (!ticketExists(ticket.getValidationCode())) {
				Event event = eventRepository.getById(ticket.getEventId());
				Ticket newTicket = new Ticket();
				newTicket.setBasicInfo(ticket.getBasicInfo());
				newTicket.setValidationCode(ticket.getValidationCode());
				newTicket.setStatus(TicketStatus.NOTVALIDATED);
				newTicket.setEvent(event);
				newTickets.add(newTicket);
				LOG.info("Saving new ticket with validationCode: {}", ticket.getValidationCode());
			} else {
				LOG.info("Ticket with validationCode {} already exists", ticket.getValidationCode());
			}
		});
		return ticketRepository.saveAll(newTickets);
	}


	public boolean ticketExists(String validationCode) {
		try {
			Ticket ticket = ticketRepository.findByValidationCode(validationCode);
			if (ticket != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


	public boolean validateTicket(Long id) {
		Ticket ticket = new Ticket();
		ticket = ticketRepository.getById(id);
		if (ticket.getStatus() == TicketStatus.NOTVALIDATED) {
			ticket.setStatus(TicketStatus.VALIDATED);
			ticketRepository.save(ticket);
			return true;
		} else if (ticket.getStatus() == TicketStatus.VALIDATED) {
			return false;
		}

		return false;
	}

	public List<Ticket> getAllTicketsForEvent(Long eventId) {
		List<Ticket> availableTickets = new ArrayList<>();
		try {
			availableTickets = ticketRepository.findAllByEvent(eventRepository.getById(eventId));
		} catch (Exception ex) {
			LOG.info("Unexpected exception while loading tickets from database ", ex);
		}
		return availableTickets;
	}
}

