package com.example.homework.entity;

import com.example.homework.entity.enums.TicketStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = true)
	private String basicInfo;

	@Column(nullable = false, updatable = false, unique = true)
	private String validationCode;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	private Event event;
}
