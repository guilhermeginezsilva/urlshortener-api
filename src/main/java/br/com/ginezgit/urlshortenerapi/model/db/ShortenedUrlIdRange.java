package br.com.ginezgit.urlshortenerapi.model.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHORTENED_URLS_ID_RANGES")
public class ShortenedUrlIdRange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rangeId;
	private Integer firstId;
	private Integer lastId;
	private String owner;
	private Integer currentId;
	private Date lastModified;

	public ShortenedUrlIdRange() {
	}

	public ShortenedUrlIdRange(Integer firstId, Integer lastId, String owner, Integer currentId,
			Date lastModified) {
		super();
		this.firstId = firstId;
		this.lastId = lastId;
		this.owner = owner;
		this.currentId = currentId;
		this.lastModified = lastModified;
	}
	
	
	public int nextId() {
		return this.currentId++;
	}
	
	public boolean hasEnded() {
		return this.currentId > this.lastId;
	}
	
	public boolean isLastId() {
		return this.currentId == this.lastId;
	}
	
	public Integer getRangeId() {
		return rangeId;
	}

	public void setRangeId(Integer rangeId) {
		this.rangeId = rangeId;
	}

	public Integer getFirstId() {
		return firstId;
	}

	public void setFirstId(Integer firstId) {
		this.firstId = firstId;
	}

	public Integer getLastId() {
		return lastId;
	}

	public void setLastId(Integer lastId) {
		this.lastId = lastId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
