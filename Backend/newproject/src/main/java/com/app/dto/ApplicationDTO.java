
package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDTO {
	private int applicationID;
	private int propertyID;
	private int tenantID;
	private LocalDate applicationDate;
	private String status; // "Pending", "Accepted", "Rejected"
}
