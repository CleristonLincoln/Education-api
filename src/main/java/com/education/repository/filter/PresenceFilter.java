package com.education.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresenceFilter {

	private LocalDate dateEventFrom;
	private LocalDate dateEventTo;
	private String situation;
}
