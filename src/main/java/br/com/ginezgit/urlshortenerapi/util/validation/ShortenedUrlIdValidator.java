package br.com.ginezgit.urlshortenerapi.util.validation;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameter;

public class ShortenedUrlIdValidator implements Validator<String> {

	@Override
	public ValidationResult validate(String parameterName, String id) throws RuntimeException {
		ValidationResult validationResult = new ValidationResult();

		if (id == null || id.isEmpty()) {
			validationResult
					.addInvalidParameter(new InvalidParameter(parameterName, "Shortened Url Id mustn't be null or empty"));
			return validationResult;
		}
		if (!id.matches("[0-9|a-z|A-Z]*")) {
			validationResult.addInvalidParameter(new InvalidParameter(parameterName, "Shortened Url Id has invalid characters"));
		}
		if (id.length() != 7) {
			validationResult.addInvalidParameter(
					new InvalidParameter(parameterName, "Shortened Url Id must have a length of 7 characters"));
		}

		return validationResult;
	}
	
	public static ValidationResult doValidation(String parameterName, String id) {
		return new ShortenedUrlIdValidator().validate(parameterName, id);
	}

}
