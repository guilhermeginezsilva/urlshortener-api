package br.com.ginezgit.urlshortenerapi.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ginezgit.urlshortenerapi.model.db.Url;

public class ShortenedUrlDto {

	@JsonProperty("originalUrl")
	private final String originalUrl;

	@JsonProperty("shortenedUrl")
	private final String shortenedUrl;

	@JsonIgnore
	private static final String serverPrefix = "http://localhost/shu/";
	
	public ShortenedUrlDto() {
		this.originalUrl = null;
		this.shortenedUrl = null;
	}
	
	public ShortenedUrlDto(Url urlRecord) {
		this.originalUrl = urlRecord.getOriginalUrl();
		this.shortenedUrl = serverPrefix+urlRecord.getShortenedUrlId();
	}

	public ShortenedUrlDto(String originalUrl, String shortenedUrl) {
		this.originalUrl = originalUrl;
		this.shortenedUrl = shortenedUrl;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

}
