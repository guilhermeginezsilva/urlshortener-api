package br.com.ginezgit.urlshortenerapi.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(name= "URLS", 
	indexes = {@Index(name = "i_original_url", columnList = "originalUrl")}
)
public class Url {

	@Id
	private final String shortenedUrlId;
	private final Integer shortenedUrlIdSeed;
	private final String originalUrl;

	public Url() {
		this.shortenedUrlId = null;
		this.shortenedUrlIdSeed = null;
		this.originalUrl = null;
	}

	public Url(String shortenedUrlId, int shortenedUrlIdSeed, String originalUrl) {
		this.shortenedUrlId = shortenedUrlId;
		this.shortenedUrlIdSeed = shortenedUrlIdSeed;
		this.originalUrl = originalUrl;
	}

	public String getShortenedUrlId() {
		return shortenedUrlId;
	}

	public Integer getShortenedUrlIdSeed() {
		return shortenedUrlIdSeed;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

}
