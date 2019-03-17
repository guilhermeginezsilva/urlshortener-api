package br.com.ginezgit.urlshortenerapi.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.ginezgit.urlshortenerapi.model.rest.ShortenedUrlDto;
import br.com.ginezgit.urlshortenerapi.service.UrlsService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortenedUrlsController.class)
public class ShortenedUrlsControllerGetShortenedUrlTest {

	private static final String REST_DOMAIN = "/v1/";
	private static final String SHORTENED_URL_ABBREVIATION = "shu";
	private static final String REST_DIRECTORY = REST_DOMAIN + SHORTENED_URL_ABBREVIATION;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UrlsService shortenedUrlsService;

	private ShortenedUrlDto mockedShortenedUrl;
	private String mockedShortenedUrlId = "0000000";

	@Before
	public void prepareTest() {
		this.mockedShortenedUrl = new ShortenedUrlDto("http://www.google.com.br",
				"http://localhost:8080/shu/" + mockedShortenedUrlId);
	}

	@Test
	public void Should_ReturnShortenUrlId_When_OriginalUrlIsValid() throws Exception {
		given(shortenedUrlsService.shortenUrl(mockedShortenedUrl.getOriginalUrl())).willReturn(this.mockedShortenedUrl);
		
		mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON).content("{\"url\": \"" + mockedShortenedUrl.getOriginalUrl() + "\"}"))
		.andExpect(jsonPath("$.originalUrl", is(mockedShortenedUrl.getOriginalUrl())))
		.andExpect(jsonPath("$.shortenedUrl", is(mockedShortenedUrl.getShortenedUrl())));
	}
	
	@Test
	public void Should_ReturnBadRequest_When_NoContentBody() throws Exception {
		mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void Should_ReturnBadRequest_When_NoInputUrl() throws Exception {
		mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON).content("{}"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void Should_ReturnBadRequest_When_InputUrlIsNullOrEmpty() throws Exception {
		mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON).content("{\"url\": \"\"}"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void Should_ReturnBadRequest_When_InputUrlIsText() throws Exception {
		String testUrl = "teste";
		mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON).content("{\"url\": \""+testUrl+"\"}"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void Should_ReturnBadRequest_When_InputUrlIsInvalid() throws Exception {
		List<String> invalidTestUrls = Arrays.asList(
			"teste",
			"teste.",
			".teste",
			"http://www",
			"http://www.",
			"http://www.g"
		);
		
		for(String url: invalidTestUrls) {
			mvc.perform(post(REST_DIRECTORY + "/shorten").contentType(MediaType.APPLICATION_JSON).content("{\"url\": \""+url+"\"}"))
			.andExpect(status().isBadRequest());
		}
	}
	
}
