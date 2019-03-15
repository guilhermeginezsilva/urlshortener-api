package br.com.ginezgit.urlshortenerapi.exception;

public class OriginalUrlNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520300529921580142L;

	public OriginalUrlNotFoundException() {
		super();
	}

	public OriginalUrlNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OriginalUrlNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OriginalUrlNotFoundException(String message) {
		super(message);
	}

	public OriginalUrlNotFoundException(Throwable cause) {
		super(cause);
	}

	
	
	
}
