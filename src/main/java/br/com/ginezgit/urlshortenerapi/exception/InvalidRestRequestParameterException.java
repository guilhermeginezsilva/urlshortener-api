package br.com.ginezgit.urlshortenerapi.exception;

import java.util.List;

public class InvalidRestRequestParameterException extends InvalidParameterException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2953421249059981924L;

	public InvalidRestRequestParameterException() {
		super();
	}

	public InvalidRestRequestParameterException(InvalidParameter... invalidParameters) {
		super(invalidParameters);
	}

	public InvalidRestRequestParameterException(InvalidParameter invalidParameter) {
		super(invalidParameter);
	}

	public InvalidRestRequestParameterException(List<InvalidParameter> invalidParameters) {
		super(invalidParameters);
	}

	public InvalidRestRequestParameterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidRestRequestParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRestRequestParameterException(String message) {
		super(message);
	}

	public InvalidRestRequestParameterException(Throwable cause) {
		super(cause);
	}

}