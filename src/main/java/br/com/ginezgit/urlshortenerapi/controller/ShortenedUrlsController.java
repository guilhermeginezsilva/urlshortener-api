package br.com.ginezgit.urlshortenerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ginezgit.urlshortenerapi.exception.InvalidRestRequestParameterException;
import br.com.ginezgit.urlshortenerapi.model.rest.GetShortenedUrlRequestDto;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;
import br.com.ginezgit.urlshortenerapi.service.UrlsService;
import br.com.ginezgit.urlshortenerapi.util.validation.ShortenedUrlIdValidator;
import br.com.ginezgit.urlshortenerapi.util.validation.UrlValidator;

@CrossOrigin
@RestController
public class ShortenedUrlsController {

	private static final String REST_DOMAIN = "/v1/";
	private static final String SHORTENED_URL_ABBREVIATION = "shu";
	public static final String REST_DIRECTORY = REST_DOMAIN + SHORTENED_URL_ABBREVIATION;

	@Autowired
	private UrlsService shortenedUrlsService;

	@RequestMapping(value = REST_DIRECTORY + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> redirectToOriginalUrl(
		@PathVariable(value = "id")
		String shortenedUrlId) {
		
		ShortenedUrlIdValidator.doValidation("id", shortenedUrlId).ifInvalidThrow((invalidParameters) -> new InvalidRestRequestParameterException(invalidParameters));
		
		ShortenedUrlDto shortenedUrl = shortenedUrlsService.getOriginalUrl(shortenedUrlId);
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, shortenedUrl.getOriginalUrl()).build();
	}

	@RequestMapping(value = REST_DIRECTORY + "/shorten", method = RequestMethod.POST)
	public ResponseEntity<?> getShortenedUrl(@RequestBody GetShortenedUrlRequestDto requestDto) {
		
		UrlValidator.doValidation("url", requestDto.getUrl()).ifInvalidThrow((invalidParameters) -> new InvalidRestRequestParameterException(invalidParameters));
		
		ShortenedUrlDto shortenedUrl = shortenedUrlsService.shortenUrl(requestDto.getUrl());
		return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
	}

}
