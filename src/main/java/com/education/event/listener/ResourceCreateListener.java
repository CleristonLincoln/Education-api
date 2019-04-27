package com.education.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.education.event.ResourceCreateEvent;

@Component
public class ResourceCreateListener implements ApplicationListener<ResourceCreateEvent>{

	
	public void onApplicationEvent(ResourceCreateEvent event) {

		HttpServletResponse response = event.getResponse();
		Long id = event.getId();
		
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {

		URI uri= ServletUriComponentsBuilder
				.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id)
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

	
	
}
