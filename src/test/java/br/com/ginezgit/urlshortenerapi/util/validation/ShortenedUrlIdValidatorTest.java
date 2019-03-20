package br.com.ginezgit.urlshortenerapi.util.validation;

import org.junit.Assert;
import org.junit.Test;

public class ShortenedUrlIdValidatorTest {

	
	
	String validId = "01234";
	String validId2 = "56789";
	String validId3 = "abcde";
	String validId4 = "fghij";
	String validId5 = "klmno";
	String validId6 = "pqrst";
	String validId7 = "uvwxy";
	String validId8 = "0000z";
	String validId9 = "ABCDE";
	String validId10 = "FGHIJ";
	String validId11 = "KLMNO";
	String validId12 = "PQRST";
	String validId13 = "UVWXY";
	String validId14 = "0000Z";
	
	String invalidCharactersId = "000@0";
	
	String nullId = null;
	String emptyId = "";
	
	String ShorterId = "0000";
	String LongerId = "000000";
	
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
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId11).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId12).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId13).isInvalid());
		Assert.assertFalse(ShortenedUrlIdValidator.doValidation("id", validId14).isInvalid());
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
