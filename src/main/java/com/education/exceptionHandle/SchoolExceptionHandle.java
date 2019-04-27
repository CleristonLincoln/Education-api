package com.education.exceptionHandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class SchoolExceptionHandle extends ResponseEntityExceptionHandler {

	@Autowired
	MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

			List<Erro> errors = errorList(ex.getBindingResult());
		
			return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String messageUser = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.getCause() != null ? ex.getCause().toString() : toString();
		
		List<Erro> errors = Arrays.asList(new Erro(messageUser,messageDeveloper));
		
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String messageUser = messageSource.getMessage("entity.not.found", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	public List<Erro> errorList(BindingResult bindingResult){
		List<Erro> error = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDeveloper = fieldError.toString();
			error.add(new Erro(messageUser, messageDeveloper));
		}
		return error;
	}

	@AllArgsConstructor 
	public static class Erro {
		@Getter
		private String messageUser;
		@Getter
		private String messageDeveloper;
	}

}
