package br.com.ginezgit.urlshortenerapi.exception;

public class IdGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520300529921580142L;

	public IdGenerationException() {
		super();
	}

	public IdGenerationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IdGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdGenerationException(String message) {
		super(message);
	}

	public IdGenerationException(Throwable cause) {
		super(cause);
	}

	
	
	
}
