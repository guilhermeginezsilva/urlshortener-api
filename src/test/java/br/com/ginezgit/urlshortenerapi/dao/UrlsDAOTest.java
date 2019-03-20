package br.com.ginezgit.urlshortenerapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ginezgit.urlshortenerapi.model.db.Url;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlsDAOTest {

	@Autowired
    private UrlsDAO urlsDAO;
	
	private String url1Id = "0000000";
	private String url1OriginalUrl = "www.google.com.br";
	private Url url1 = new Url(url1Id, 0, url1OriginalUrl, new Date());
	private String url2Id = "instance2";
	private String url2OriginalUrl = "http://localhost:8080";
	private Url url2 = new Url(url2Id, 1, url2OriginalUrl, new Date());
	
	private String noExistentOriginalUrl = "www.teste.com.br";
	
	@After
	public void afterTests() {
		urlsDAO.deleteAll();
	}
	
	@Test
	public void Should_FindByOriginalUrl_When_ItExists() {
		urlsDAO.save(url1);
		urlsDAO.save(url2);
		
		Optional<Url> found = urlsDAO.findByOriginalUrl(url1OriginalUrl);
	 
	    assertTrue(found.isPresent());
	    assertEquals(url1.getShortenedUrlId(), found.get().getShortenedUrlId());
	    
	    Optional<Url> found2 = urlsDAO.findByOriginalUrl(url2OriginalUrl);
		 
	    assertTrue(found2.isPresent());
	    assertEquals(url2.getShortenedUrlId(), found2.get().getShortenedUrlId());
	}
	
	@Test
	public void Should_DontFindByOriginalUrl_When_ItDoesntExists() {
		Optional<Url> found = urlsDAO.findByOriginalUrl(noExistentOriginalUrl);
	    assertFalse(found.isPresent());
	}
	
}
