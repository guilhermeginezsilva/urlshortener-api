package br.com.ginezgit.urlshortenerapi.controller.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameterException;
import br.com.ginezgit.urlshortenerapi.exception.InvalidRestRequestParameterException;
import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	private static final String DEFAULT_INTERNAL_SERVER_ERROR_EXTERNAL_MESSAGE = "Sorry, an error has ocurred during the request processing, please try again later or contact our support";
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> unexpectedExceptions(Exception exception, WebRequest request) {
		logger.error("An Exception has ocurred in runtime", exception);
		return new ResponseEntity<>(DEFAULT_INTERNAL_SERVER_ERROR_EXTERNAL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	protected ResponseEntity<Object> invalidParameterException(InvalidParameterException exception, WebRequest request) {
		logger.debug(exception.toString(), exception);
		return new ResponseEntity<>(DEFAULT_INTERNAL_SERVER_ERROR_EXTERNAL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidRestRequestParameterException.class)
	protected ResponseEntity<Object> invalidParameterException(InvalidRestRequestParameterException exception, WebRequest request) {
		logger.debug(exception.toString(), exception);
		return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OriginalUrlNotFoundException.class)
	protected ResponseEntity<Object> originalUrlNotFoundException(OriginalUrlNotFoundException exception, WebRequest request) {
		logger.debug(exception.getMessage(), exception);
		String externalMessage = "The requested original Url couldn't be found";
		return new ResponseEntity<>(externalMessage, HttpStatus.NOT_FOUND);
	}
	

}
