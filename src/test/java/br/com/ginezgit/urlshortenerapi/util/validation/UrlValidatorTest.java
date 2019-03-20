package br.com.ginezgit.urlshortenerapi.util.validation;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UrlValidatorTest {

	String invalidCharactersUrl = "www.!@#$%¨&*()_+-=/;.,<>]~^}[´`{.com.br";
	
	String nullUrl = null;
	String emptyUrl = "";
	
	
	@Test
	public void Should_NotReturnInvalidParameters_When_UrlIsValid() {
		List<String> invalidTestUrls = Arrays.asList(
			"http://www.go",
			"http://google.br",
			"http://google.com.br",
			"http://www.google.com.br",
			"https://www.go",
			"https://google.br",
			"https://google.com.br",
			"https://www.google.com.br",
			"ftp://www.go",
			"ftp://google.br",
			"ftp://google.com.br",
			"ftp://www.google.com.br",
			"file://www.go",
			"file://google.br",
			"file://google.com.br",
			"file://www.google.com.br"
		);
		
		for(String url: invalidTestUrls) {
			Assert.assertFalse(UrlValidator.doValidation("id", url).isInvalid());
		}
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_UrlIsNull() {
		ValidationResult validation = UrlValidator.doValidation("id", nullUrl);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_UrlIsEmpty() {
		ValidationResult validation = UrlValidator.doValidation("id", emptyUrl);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_UrlHasInvalidCharacters() {
		ValidationResult validation = UrlValidator.doValidation("id", invalidCharactersUrl);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	
}
