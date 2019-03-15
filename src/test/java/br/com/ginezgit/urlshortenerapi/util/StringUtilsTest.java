package br.com.ginezgit.urlshortenerapi.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.ginezgit.urlshortenerapi.exception.InvalidParameterException;
import br.com.ginezgit.urlshortenerapi.util.StringUtils.Side;

public class StringUtilsTest {

	private static final String DEFAULT_VALUE = "123SomeValue"; 

	@Test(expected = InvalidParameterException.class)
    public void paddingWithNullValueParameter() {
		StringUtils.padding(null, 15, '0', Side.LEFT);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void paddingWithNullCharParameter() {
		StringUtils.padding(DEFAULT_VALUE, 15, null, Side.LEFT);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void paddingWithNullSideParameter() {
		StringUtils.padding(DEFAULT_VALUE, 15, '0', null);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void paddingLeftWithTargetLengthLowerThanValueLength() {
		String result = StringUtils.padding(DEFAULT_VALUE, 10, '0', Side.LEFT);
    }
	
	@Test
    public void paddingLeftWithTargetLengthEqualsToValueLength() {
		String expected = DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 12, '0', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingLeftWithNumber() {
		String expected = "000"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '0', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingLeftWithChars() {
		String expected = "HHH"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, 'H', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingLeftWithSymbols() {
		String expected = "@@@"+DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '@', Side.LEFT);
		Assert.assertEquals(expected, result);
    }
	
	@Test(expected = InvalidParameterException.class)
    public void paddingRightWithTargetLengthLowerThanValueLength() {
		StringUtils.padding(DEFAULT_VALUE, 10, '0', Side.RIGHT);
    }
	
	@Test
    public void paddingRightWithTargetLengthEqualsToValueLength() {
		String expected = DEFAULT_VALUE;
		String result = StringUtils.padding(DEFAULT_VALUE, 12, '0', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingRightWithNumber() {
		String expected = DEFAULT_VALUE+"000";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '0', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingRightWithChars() {
		String expected = DEFAULT_VALUE+"HHH";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, 'H', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
	@Test
    public void paddingRightWithSymbols() {
		String expected = DEFAULT_VALUE+"@@@";
		String result = StringUtils.padding(DEFAULT_VALUE, 15, '@', Side.RIGHT);
		Assert.assertEquals(expected, result);
    }
	
}
