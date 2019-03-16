package br.com.ginezgit.urlshortenerapi.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvalidParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520300529921580142L;

	private List<InvalidParameter> invalidParameters = new ArrayList<InvalidParameter>();

	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(Throwable cause) {
		super(cause);
	}

	public InvalidParameterException(InvalidParameter invalidParameter) {
		super();
		this.invalidParameters = Arrays.asList(invalidParameter);
	}
	
	public InvalidParameterException(List<InvalidParameter> invalidParameters) {
		super();
		this.invalidParameters = invalidParameters;
	}
	
	public InvalidParameterException(InvalidParameter... invalidParameters) {
		super();
		this.invalidParameters = Arrays.asList(invalidParameters);
	}
	
	public List<InvalidParameter> getInvalidParameters() {
		return this.invalidParameters;
	}
	
	@Override
		public String toString() {
		
			StringBuilder toString = new StringBuilder("Invalid Parameters:\n");
			toString.append(
					this.invalidParameters.stream().collect(StringBuilder::new,
					(parameterMessage, element) -> {
						parameterMessage.append("'").append(element.getParameter()).append("': ").append(element.getMessage()).append("\n");
					},
					(accumulatedParametersMessages, parameterMessage) -> {})
					.toString()
					);
			 
			 return toString.toString();
		}

}