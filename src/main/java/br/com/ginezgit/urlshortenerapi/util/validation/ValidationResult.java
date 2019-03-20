package br.com.ginezgit.urlshortenerapi.util.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameter;

public class ValidationResult {

	private List<InvalidParameter> invalidParameters = new ArrayList<InvalidParameter>();

	public ValidationResult() {
	}

	public ValidationResult(List<InvalidParameter> invalidParameters) {
		super();
		this.invalidParameters = invalidParameters;
	}

	public List<InvalidParameter> getInvalidParameters() {
		return this.invalidParameters;
	}

	public void ifInvalidThrow(Function<List<InvalidParameter>, RuntimeException> function) throws RuntimeException {
		if (isInvalid()) {
			throw function.apply(this.invalidParameters);
		}
	}

	public boolean isInvalid() {
		return this.invalidParameters.size() > 0;
	}

	public void addInvalidParameter(InvalidParameter parameter) {
		this.invalidParameters.add(parameter);
	}

	public void setInvalidParameters(List<InvalidParameter> invalidParameters) {
		this.invalidParameters = invalidParameters;
	}

}