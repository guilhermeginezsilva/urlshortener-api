package br.com.ginezgit.urlshortenerapi.util;

import br.com.ginezgit.urlshortenerapi.exception.IdGenerationException;

public interface IdGenerator {
	
	public int getNewId() throws IdGenerationException;
	public String getNewIdOnBase(int newBase) throws IdGenerationException;
	public String getNewIdOnBase62() throws IdGenerationException;
	
}
