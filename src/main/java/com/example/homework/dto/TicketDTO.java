package com.example.homework.dto;

import com.example.homework.entity.enums.TicketStatus;
import lombok.Data;

@Data
public class TicketDTO {
	private String basicInfo;
	private String validationCode;
	private TicketStatus status;
}
