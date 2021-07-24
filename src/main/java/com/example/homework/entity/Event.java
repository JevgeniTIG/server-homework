package com.example.homework.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = true)
	private String basicInfo;

	@Column(nullable = false)
	private String location;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(updatable = false)
	private LocalDateTime timeOfEvent;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event", orphanRemoval = true)
	private List<Ticket> tickets;

	{
		tickets = new ArrayList<>();
	}
}
