package br.com.ginezgit.urlshortenerapi.util.validation;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameter;

public class NotNullValidator implements Validator<Object> {

	@Override
	public ValidationResult validate(String parameterName, Object object) throws RuntimeException {
		ValidationResult validationResult = new ValidationResult();

		if (object == null) {
			validationResult
					.addInvalidParameter(new InvalidParameter(parameterName, "Mustn't be null"));
		}
		
		return validationResult;
	}

	public static ValidationResult doValidation(String parameterName, Object object) {
		return new NotNullValidator().validate(parameterName, object);
	}
	
}
