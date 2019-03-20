package br.com.ginezgit.urlshortenerapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ginezgit.urlshortenerapi.dao.UrlsDAO;
import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;
import br.com.ginezgit.urlshortenerapi.model.db.Url;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlsServiceTest {

	@TestConfiguration
    static class ShortenedUrlsServiceTestContextConfiguration {
  
        @Bean
        public UrlsService shortenedUrlsService() {
            return new UrlsService();
        }
    }
 
    @Autowired
    private UrlsService urlsService;
    
    @MockBean
    private UrlsDAO urlsDAO;
	
    private Url url1 = new Url("0000000", 0, "http://www.google.com.br", new Date());
    private Url url1WithouHttp = new Url("0000000", 0, "www.google.com.br", new Date());
    private Url url3633525 = new Url("000ffff", 3633525, "http://www.teste.com.br", new Date());
    
	@Test
	public void Should_GetOriginalUrl_When_Exists() {
		Mockito.doReturn(Optional.of(url1)).when(urlsDAO).findById(any(String.class));
		
		ShortenedUrlDto foundUrl = urlsService.getOriginalUrl(url1.getShortenedUrlId());
		assertTrue(foundUrl.getShortenedUrl().endsWith(url1.getShortenedUrlId()));
		assertEquals(url1.getOriginalUrl(), foundUrl.getOriginalUrl());
	}
	
	@Test(expected = OriginalUrlNotFoundException.class)
	public void Should_ThrowOriginalUrlNotFoundException_When_OriginalUrlDontExists() {
		Mockito.doReturn(Optional.empty()).when(urlsDAO).findById(any(String.class));
		urlsService.getOriginalUrl(url1.getShortenedUrlId());
	}
	
	@Test
	public void Should_ShortenUrl_When_UrlIsValidAndDontExists() {
		Mockito.doReturn(Optional.empty()).when(urlsDAO).findByOriginalUrl(any(String.class));
		
		ShortenedUrlDto foundUrl = urlsService.shortenUrl(url3633525.getOriginalUrl());
		
		assertFalse(foundUrl.getShortenedUrl().endsWith(url3633525.getShortenedUrlId()));
		assertEquals(url3633525.getOriginalUrl(), foundUrl.getOriginalUrl());
	}
	
	@Test
	public void Should_GetExistingShortenedUrl_When_UrlAlreadyExists() {
		Mockito.doReturn(Optional.of(url3633525)).when(urlsDAO).findByOriginalUrl(any(String.class));
		
		ShortenedUrlDto foundUrl = urlsService.shortenUrl(url3633525.getOriginalUrl());
		
		assertTrue(foundUrl.getShortenedUrl().endsWith(url3633525.getShortenedUrlId()));
		assertEquals(url3633525.getOriginalUrl(), foundUrl.getOriginalUrl());
	}
	
	@Test
	public void Should_ShortenUrlWithHttp_When_UrlIsWwwAndProtocolIsMissing() {
		Mockito.doReturn(Optional.of(url1)).when(urlsDAO).findByOriginalUrl(url1.getOriginalUrl());
		
		ShortenedUrlDto foundUrl = urlsService.shortenUrl(url1WithouHttp.getOriginalUrl());
		assertTrue(foundUrl.getShortenedUrl().endsWith(url1.getShortenedUrlId()));
		assertEquals(url1.getOriginalUrl(), foundUrl.getOriginalUrl());
	}
	
	
}
