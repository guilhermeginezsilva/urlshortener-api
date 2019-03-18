package br.com.ginezgit.urlshortenerapi.controller;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.ginezgit.urlshortenerapi.exception.OriginalUrlNotFoundException;
import br.com.ginezgit.urlshortenerapi.id.IdGeneratorImpl;
import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;
import br.com.ginezgit.urlshortenerapi.service.UrlsService;
import br.com.ginezgit.urlshortenerapi.util.StringUtils;
import br.com.ginezgit.urlshortenerapi.util.StringUtils.Side;


@RunWith(SpringRunner.class)
@WebMvcTest(ShortenedUrlsController.class)
public class ShortenedUrlsControllerRedirectToOriginalUrlTest {
	
	private static final String REST_DOMAIN="/v1/";
	private static final String SHORTENED_URL_ABBREVIATION = "shu";
	private static final String REST_DIRECTORY = REST_DOMAIN + SHORTENED_URL_ABBREVIATION;
	
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private UrlsService shortenedUrlsService;
	
    private ShortenedUrlDto mockedShortenedUrl;
    private String mockedShortenedUrlId="00000";
    
    @Before
    public void prepareTest() {
    	this.mockedShortenedUrl = new ShortenedUrlDto("http://www.google.com.br", "http://localhost:8080/shu/"+mockedShortenedUrlId);
    }
    
    @Test
    public void Should_RedirectToOriginalUrlOfShortenedUrl_When_InputIdMatchesAnExistentShortenedUrlRecordId() throws Exception {
	    given(shortenedUrlsService.getOriginalUrl(mockedShortenedUrlId)).willReturn(this.mockedShortenedUrl);
	 
	    mvc.perform(get(REST_DIRECTORY+"/"+mockedShortenedUrlId)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isMovedPermanently())
	      .andExpect(MockMvcResultMatchers.redirectedUrl(this.mockedShortenedUrl.getOriginalUrl()));
    }
	
	@Test
    public void Should_ReturnNotFound_When_InputIdDoesntMatchAnExistentShortenedUrlRecordId() throws Exception {
		given(shortenedUrlsService.getOriginalUrl(mockedShortenedUrlId)).willThrow(new OriginalUrlNotFoundException(mockedShortenedUrlId));
		
	    mvc.perform(get(REST_DIRECTORY+"/"+mockedShortenedUrlId)
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isNotFound());
    }
	
	@Test
    public void Should_ReturnNotFound_When_InputIdIsNullOrEmpty() throws Exception {
		mvc.perform(get(REST_DIRECTORY+"/")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound());
    }
	
	@Test
    public void Should_ReturnsBadRequest_When_InputIdHasLessThan7Characters() throws Exception {
		mvc.perform(get(REST_DIRECTORY+"/123456")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
    }
	
	@Test
    public void Should_ReturnsBadRequest_When_InputIdHasMoreThan7Characters() throws Exception {
		mvc.perform(get(REST_DIRECTORY+"/12345678")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest());
    }
	
	@Test
    public void Should_ReturnClientError_When_InputIdHasInvalidCharacters() throws Exception {
		int ASCII_SYMBOLS_START = 32;
		int ASCII_SYMBOLS_END = 126;
		
		for(int i =ASCII_SYMBOLS_START; i<= ASCII_SYMBOLS_END; i++) {
			Character asciiChar = (char) i;
			String generatedId = StringUtils.padding("", IdGeneratorImpl.DEFAULT_ID_LENGTH, asciiChar, Side.LEFT);
			
			if(asciiChar.toString().matches("[0-9|A-Z|a-z]*")) {
				given(shortenedUrlsService.getOriginalUrl(generatedId)).willReturn(this.mockedShortenedUrl);
				mvc.perform(get(REST_DIRECTORY+"/"+generatedId)
					      .contentType(MediaType.APPLICATION_JSON))
					      .andExpect(status().isMovedPermanently());
			} else {
				mvc.perform(get(REST_DIRECTORY+"/"+generatedId)
					      .contentType(MediaType.APPLICATION_JSON))
					      .andExpect(status().is4xxClientError());
			}
		}
    }
	
}
