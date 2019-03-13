package br.com.ginezgit.urlshortenerapi.util;

import br.com.ginezgit.urlshortenerapi.exception.IdGenerationException;

public class IdGeneratorImpl implements IdGenerator {

	private int currentBase10Id = 0;
	
	@Override
	public int getNewId() throws IdGenerationException {
		return currentBase10Id++;
	}

	@Override
	public String getNewIdOnBase(int newBase) throws IdGenerationException {
		return Base10Converter.convert(getNewId(), newBase);
	}
	
	@Override
	public String getNewIdOnBase62() throws IdGenerationException {
		return Base10Converter.convert(getNewId(), 62);
	}

}
