package br.com.ginezgit.urlshortenerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;
import br.com.ginezgit.urlshortenerapi.model.db.Url;
import br.com.ginezgit.urlshortenerapi.model.rest.GetShortenedUrlRequestDto;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;
import br.com.ginezgit.urlshortenerapi.service.ShortenedUrlsService;

@RestController
public class ShortenedUrlsController {

	private static final String SHORTENED_URL_ABBREVIATION = "shu";
	private static final String REST_DIRECTORY = "/" + SHORTENED_URL_ABBREVIATION + "/";

	@Autowired
	private ShortenedUrlsService shortenedUrlsService;

	/*@RequestMapping(value = REST_DIRECTORY + "/{id}/data", method = RequestMethod.GET)
	public ResponseEntity<?> getOriginalUrl(@PathVariable(value = "id", required = true) String shortenedUrlId) {
		try {
			ShortenedUrlDto shortenedUrl = shortenedUrlsService.getOriginalUrl(shortenedUrlId);
			return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
		} catch (OriginalUrlNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}*/
	
	@RequestMapping(value = REST_DIRECTORY + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable(value = "id", required = true) String shortenedUrlId) {
		try {
			ShortenedUrlDto shortenedUrl = shortenedUrlsService.getOriginalUrl(shortenedUrlId);
			return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, shortenedUrl.getOriginalUrl()).build();
		} catch (OriginalUrlNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = REST_DIRECTORY + "/shorten", method = RequestMethod.POST)
	public ResponseEntity<?> getShortenedUrl(@RequestBody GetShortenedUrlRequestDto requestDto) {
		try {
			ShortenedUrlDto shortenedUrl = shortenedUrlsService.shortenUrl(requestDto.getUrl());
			return new ResponseEntity<>(shortenedUrl, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
