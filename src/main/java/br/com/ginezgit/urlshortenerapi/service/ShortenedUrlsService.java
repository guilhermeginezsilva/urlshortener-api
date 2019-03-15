package br.com.ginezgit.urlshortenerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ginezgit.urlshortenerapi.dao.ShortenedUrlsDAO;
import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;
import br.com.ginezgit.urlshortenerapi.id.GeneratedId;
import br.com.ginezgit.urlshortenerapi.id.IdGenerator;
import br.com.ginezgit.urlshortenerapi.model.db.Url;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;

@Service
public class ShortenedUrlsService {

	@Autowired
	private ShortenedUrlsDAO shortenedUrlsDAO;
	@Autowired
	IdGenerator idGenerator;
	
	public ShortenedUrlDto getOriginalUrl(String shortenedUrlId) {
		return new ShortenedUrlDto(shortenedUrlsDAO.findById(shortenedUrlId)
				.orElseThrow(() -> new OriginalUrlNotFoundException(shortenedUrlId))
				);
	}
	
	public ShortenedUrlDto shortenUrl(String urlToShorten) {
		String fixedUrlToShorten = fixPossibleUrlProblems(urlToShorten);
		
		Optional<Url> alreadyExistingUrlRecord = shortenedUrlsDAO.findByOriginalUrl(fixedUrlToShorten);
		if(alreadyExistingUrlRecord.isPresent()) {
			return new ShortenedUrlDto(alreadyExistingUrlRecord.get());
		}
		
		return createAndGetNewUrlRecord(fixedUrlToShorten);
	}

	private ShortenedUrlDto createAndGetNewUrlRecord(String fixedUrlToShorten) {
		GeneratedId generatedId = idGenerator.getNewId();
		Url urlRecord = new Url(generatedId.getGeneratedId(), generatedId.getGenerationSeed(), fixedUrlToShorten);
		shortenedUrlsDAO.save(urlRecord);
		
		return new ShortenedUrlDto(urlRecord);
	}

	private String fixPossibleUrlProblems(String urlToShorten) {
		String fixedUrl = urlToShorten;
		
		if(fixedUrl.startsWith("www.")) {
			fixedUrl = "http://"+fixedUrl;
		}
		
		return fixedUrl;
	}
	
}
