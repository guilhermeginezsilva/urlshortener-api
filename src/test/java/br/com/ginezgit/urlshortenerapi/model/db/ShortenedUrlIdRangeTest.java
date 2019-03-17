package br.com.ginezgit.urlshortenerapi.model.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class ShortenedUrlIdRangeTest {

	private String rangeOwner = "instance1";
	
	@Test
	public void Should_ReturnTrue_When_IsLastId() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(0, 1, rangeOwner, 1, new Date());
	    assertTrue(range.isLastId());
	}
	
	@Test
	public void Should_ReturnFalse_When_IsNotLastId() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(0, 1, rangeOwner, 0, new Date());
	    assertFalse(range.isLastId());
	}
	
	@Test
	public void Should_ReturnTrue_When_IdsHasEnded() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(0, 1, rangeOwner, 2, new Date());
	    assertTrue(range.hasEnded());
	}
	
	@Test
	public void Should_ReturnFalse_When_IdsHasNotEnded() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(0, 1, rangeOwner, 0, new Date());
	    assertFalse(range.hasEnded());
	}
	
}
