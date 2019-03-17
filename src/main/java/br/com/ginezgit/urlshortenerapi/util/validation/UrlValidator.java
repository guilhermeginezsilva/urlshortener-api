package br.com.ginezgit.urlshortenerapi.util.validation;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameter;

public class UrlValidator implements Validator<String> {

	@Override
	public ValidationResult validate(String parameterName, String url) throws RuntimeException {
		ValidationResult validationResult = new ValidationResult();

		if (url == null || url.isEmpty()) {
			validationResult
					.addInvalidParameter(new InvalidParameter(parameterName, "Url mustn't be null or empty"));
			return validationResult;
		}
		
		
		
		
		if (!url.matches("^[0-9|a-z|A-Z|\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,]*?\\:\\/\\/[0-9|a-z|A-Z|\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,]*?\\.[0-9|a-z|A-Z|\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,]{2}[0-9|a-z|A-Z|\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,]*")
		) {
			validationResult.addInvalidParameter(new InvalidParameter(parameterName, "Url has an invalid format"));
		}
		
		
		return validationResult;
	}

	public static ValidationResult doValidation(String parameterName, String url) {
		return new UrlValidator().validate(parameterName, url);
	}
	
	
}
