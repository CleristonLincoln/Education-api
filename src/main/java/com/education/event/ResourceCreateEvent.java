package com.education.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ResourceCreateEvent extends ApplicationEvent{

	private static final long serialVersionUID = -9048699759779327400L;
	
	@Getter	private HttpServletResponse response;
	@Getter	private Long id;
	
	public ResourceCreateEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id = id;
	}

}
