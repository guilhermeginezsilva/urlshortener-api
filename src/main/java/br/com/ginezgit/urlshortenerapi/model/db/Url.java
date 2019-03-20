package br.com.ginezgit.urlshortenerapi.model.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "URLS", indexes = { @Index(name = "i_original_url", columnList = "originalUrl") })
public class Url {

	@Id
	private String shortenedUrlId;
	private Integer shortenedUrlIdSeed;
	private String originalUrl;
	private Date lastModified;

	public Url() {
	}

	public Url(String shortenedUrlId, int shortenedUrlIdSeed, String originalUrl, Date lastModified) {
		this.shortenedUrlId = shortenedUrlId;
		this.shortenedUrlIdSeed = shortenedUrlIdSeed;
		this.originalUrl = originalUrl;
		this.lastModified = lastModified;
	}

	public String getShortenedUrlId() {
		return shortenedUrlId;
	}

	public void setShortenedUrlId(String shortenedUrlId) {
		this.shortenedUrlId = shortenedUrlId;
	}

	public Integer getShortenedUrlIdSeed() {
		return shortenedUrlIdSeed;
	}

	public void setShortenedUrlIdSeed(Integer shortenedUrlIdSeed) {
		this.shortenedUrlIdSeed = shortenedUrlIdSeed;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
