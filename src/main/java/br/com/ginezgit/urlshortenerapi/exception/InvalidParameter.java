package br.com.ginezgit.urlshortenerapi.exception;

public class InvalidParameter {
	private final String parameter;
	private final String message;

	public InvalidParameter() {
		this.parameter = null;
		this.message = null;
	}
	
	public InvalidParameter(String parameter, String message) {
		this.parameter = parameter;
		this.message = message;
	}

	public String getParameter() {
		return parameter;
	}

	public String getMessage() {
		return message;
	}

}