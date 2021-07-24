package com.example.homework.repository;

import com.example.homework.entity.Event;
import com.example.homework.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Long> {

	Ticket findByValidationCode(String validationCode);
	List<Ticket> findAllByEvent(Event event);
}
