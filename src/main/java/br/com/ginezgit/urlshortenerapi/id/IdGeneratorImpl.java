package br.com.ginezgit.urlshortenerapi.id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ginezgit.urlshortenerapi.exception.IdGenerationException;
import br.com.ginezgit.urlshortenerapi.util.Base10Converter;
import br.com.ginezgit.urlshortenerapi.util.StringUtils;
import br.com.ginezgit.urlshortenerapi.util.StringUtils.Side;

@Component
@Scope("application")
public class IdGeneratorImpl implements IdGenerator{

	private Integer currentBase10Id = 0;
	public static final int DEFAULT_ID_LENGTH = 7;
	public static final char DEFAULT_ID_PADDING_CHAR = '0';
	
	@Override
	public GeneratedId getNewId() throws IdGenerationException {
		synchronized (currentBase10Id) {
			return new GeneratedId(getNewIdOnBase62(), currentBase10Id);
		}
	}
	
	public synchronized String getNewIdOnBase(int newBase) throws IdGenerationException {
		synchronized (currentBase10Id) {
			return addPadding(Base10Converter.convert(getNewIdCounter(), newBase));
		}
	}
	
	public synchronized String getNewIdOnBase62() throws IdGenerationException {
		synchronized (currentBase10Id) {
			return addPadding(Base10Converter.convert(getNewIdCounter(), 62));
		}
	}
	
	private int getNewIdCounter() throws IdGenerationException {
		return currentBase10Id++;
	}
	
	private String addPadding(String value) {
		return StringUtils.padding(value, DEFAULT_ID_LENGTH, DEFAULT_ID_PADDING_CHAR, Side.LEFT);
	}


	
}
