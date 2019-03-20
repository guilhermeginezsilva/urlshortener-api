package br.com.ginezgit.urlshortenerapi.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameterException;
import br.com.ginezgit.urlshortenerapi.util.StringUtils.Side;

public class StringUtilsTest {

	private static final String DEFAULT_VALUE = "123SomeValue"; 

	@Test(expected = InvalidParameterException.class)
    public void Should_ThrowInvalidParameterException_ForNullInputStringValue() {
		StringUtils.padding(null, 15, '0', Side.LEFT);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void Should_ThrowInvalidParameterException_ForNullInputCharParameter() {
		StringUtils.padding(DEFAULT_VALUE, 15, null, Side.LEFT);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void Should_ThrowInvalidParameterException_ForNullSideParameter() {
		StringUtils.padding(DEFAULT_VALUE, 15, '0', null);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void Should_ThrowInvalidParameterException_ForTargetLengthParameterLowerThanValueLength() {
		String result = StringUtils.padding(DEFAULT_VALUE, 10, '0', Side.LEFT);
    }
	
	@Test
    public void Should_padLeft_ForTargetLengthParameterEqualsToValueLength() {
		String expected = DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 12, '0', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padLeft_ForNumberCharacterInput() {
		String expected = "000"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '0', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padLeft_ForLetterCharacterInput() {
		String expected = "HHH"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, 'H', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padLeft_ForSymbolCharacterInput() {
		String expected = "@@@"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '@', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padRight_ForTargetLengthParameterEqualsToValueLength() {
		String expected = DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 12, '0', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padRight_ForNumberCharacterInput() {
		String expected = DEFAULT_VALUE+"000";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '0', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padRight_ForLetterCharacterInput() {
		String expected = DEFAULT_VALUE+"HHH";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, 'H', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void Should_padRight_ForSymbolCharacterInput() {
		String expected = DEFAULT_VALUE+"@@@";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '@', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
}
