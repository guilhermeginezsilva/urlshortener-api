package br.com.ginezgit.urlshortenerapi.util.validation;

import org.junit.Assert;
import org.junit.Test;

public class ShortenedUrlIdValidatorTest {

	String validId = "0123456";
	String validId2 = "0000789";
	String validId3 = "abcdefg";
	String validId4 = "hijklmn";
	String validId5 = "opqrstu";
	String validId6 = "00vwxyz";
	String validId7 = "ABCDEFG";
	String validId8 = "HIJKLMN";
	String validId9 = "OPQRSTU";
	String validId10 = "00VWXYZ";
	
	String invalidCharactersId = "000@000";
	
	String nullId = null;
	String emptyId = "";
	
	String ShorterId = "000000";
	String LongerId = "00000000";
	
	String invalidId2Reasons = "@#$%";
	
	
	@Test
	public void Should_NotReturnInvalidParameters_When_IdIsValid() {
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId2).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId3).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId4).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId5).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId6).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId7).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId8).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId9).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId10).isInvalid());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_IdIsNull() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", nullId);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_IdIsEmpty() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", emptyId);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_IdHasInvalidCharacters() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", invalidCharactersId);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_IdIsLengthIsShorterThan7Characters() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", ShorterId);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnInvalidParameter_When_IdIsLengthIsLongerThan7Characters() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", LongerId);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(1, validation.getInvalidParameters().size());
	}
	
	@Test
	public void Should_ReturnMultipleInvalidParameters_When_IdIsInvalidForMoreThanOneReason() {
		ValidationResult validation = ShortenedUrlIdValidator.doValidation("id", invalidId2Reasons);
		Assert.assertTrue(validation.isInvalid());
		Assert.assertEquals(2, validation.getInvalidParameters().size());
	}
	
}
