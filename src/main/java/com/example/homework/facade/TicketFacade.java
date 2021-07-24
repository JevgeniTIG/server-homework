package com.example.homework.facade;

import com.example.homework.dto.TicketDTO;
import com.example.homework.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketFacade {
	public TicketDTO ticketToTicketDTO(Ticket ticket) {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setBasicInfo(ticket.getBasicInfo());
		ticketDTO.setValidationCode(ticket.getValidationCode());
		ticketDTO.setStatus(ticket.getStatus());
		return ticketDTO;
	}
}
