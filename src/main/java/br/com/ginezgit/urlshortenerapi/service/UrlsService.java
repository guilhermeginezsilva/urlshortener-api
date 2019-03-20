package br.com.ginezgit.urlshortenerapi.service;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.ginezgit.urlshortenerapi.controller.ShortenedUrlsController;
import br.com.ginezgit.urlshortenerapi.dao.UrlsDAO;
import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;
import br.com.ginezgit.urlshortenerapi.id.GeneratedId;
import br.com.ginezgit.urlshortenerapi.id.IdGenerator;
import br.com.ginezgit.urlshortenerapi.model.db.Url;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;

@Service
public class UrlsService {

	@Autowired
	private UrlsDAO shortenedUrlsDAO;
	@Autowired
	IdGenerator idGenerator;
	@Autowired
	HttpServletRequest request;
	
	@Value("${application.rest.address}")
	private String restAddress;
	
	public ShortenedUrlDto getOriginalUrl(String shortenedUrlId) {
		
		Optional<Url> urlOptional = shortenedUrlsDAO.findById(shortenedUrlId);
		Url url = urlOptional.orElseThrow(() -> new OriginalUrlNotFoundException(shortenedUrlId));
		
		return new ShortenedUrlDto(url.getOriginalUrl(), getUrlForShortenedId(url.getShortenedUrlId()));
	}
	
	public ShortenedUrlDto shortenUrl(String urlToShorten) {
		String fixedUrlToShorten = fixPossibleUrlProblems(urlToShorten);
		
		Optional<Url> alreadyExistingUrlRecord = shortenedUrlsDAO.findByOriginalUrl(fixedUrlToShorten);
		if(alreadyExistingUrlRecord.isPresent()) {
			return new ShortenedUrlDto(alreadyExistingUrlRecord.get().getOriginalUrl(), getUrlForShortenedId(alreadyExistingUrlRecord.get().getShortenedUrlId()));
		}
		
		return createAndGetNewUrlRecord(fixedUrlToShorten);
	}

	private ShortenedUrlDto createAndGetNewUrlRecord(String fixedUrlToShorten) {
		GeneratedId generatedId = idGenerator.getNewId();
		Url urlRecord = new Url(generatedId.getGeneratedId(), generatedId.getGenerationSeed(), fixedUrlToShorten, new Date());
		shortenedUrlsDAO.save(urlRecord);
		
		return new ShortenedUrlDto(urlRecord.getOriginalUrl(), getUrlForShortenedId(urlRecord.getShortenedUrlId()));
	}

	private String fixPossibleUrlProblems(String urlToShorten) {
		String fixedUrl = urlToShorten;
		
		if(fixedUrl.startsWith("www.")) {
			fixedUrl = "http://"+fixedUrl;
		}
		
		return fixedUrl;
	}
	
	private String getUrlForShortenedId(String id) {
		return getHostAndPortString() + id;
	}
	
	private String getHostAndPortString() {
		return this.restAddress + ShortenedUrlsController.REST_DIRECTORY+"/";
	}
}
