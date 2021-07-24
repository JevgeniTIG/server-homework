package com.example.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
	private Long id;
	private String basicInfo;
	private String location;
	private LocalDateTime timeOfEvent;
}
