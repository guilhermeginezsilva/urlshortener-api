package br.com.ginezgit.urlshortenerapi.id;

import br.com.ginezgit.urlshortenerapi.exception.IdGenerationException;

public interface IdGenerator {

	public GeneratedId getNewId() throws IdGenerationException;
	
}
