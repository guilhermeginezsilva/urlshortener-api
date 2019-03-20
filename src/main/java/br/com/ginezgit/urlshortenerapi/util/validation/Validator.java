package br.com.ginezgit.urlshortenerapi.util.validation;

public interface Validator<T> {

	public ValidationResult validate(String parameter, T value) throws RuntimeException;
	
}
