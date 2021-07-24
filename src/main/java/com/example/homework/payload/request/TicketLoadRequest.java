package com.example.homework.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketLoadRequest {
	private String basicInfo;
	private String validationCode;
	private Long eventId;
}
