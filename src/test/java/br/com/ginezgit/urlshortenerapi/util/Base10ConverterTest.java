package br.com.ginezgit.urlshortenerapi.util;

import org.junit.Assert;
import org.junit.Test;

public class Base10ConverterTest {

	private static final int BASE_10_NUMBER_0 = 0;
	private static final int BASE_10_NUMBER_256 = 256;
	private static final int BASE_10_NUMBER_512 = 512;
	private static final int BASE_10_NUMBER_MAX_INT = Integer.MAX_VALUE;
	
	private static final String BASE_16_NUMBER_0 = "0";
	private static final String BASE_16_NUMBER_256 = "100";
	private static final String BASE_16_NUMBER_512 = "200";
	private static final String BASE_16_NUMBER_MAX_INT = "7fffffff";
	
	private static final String BASE_62_NUMBER_0 = "0";
	private static final String BASE_62_NUMBER_256 = "48";
	private static final String BASE_62_NUMBER_512 = "8g";
	private static final String BASE_62_NUMBER_MAX_INT = "2lkCB1";
	
	private static final int BASE_16 = 16;
	private static final int BASE_62 = 62;
	
	
    @Test
    public void convertToBase16() {
    	Assert.assertEquals(BASE_16_NUMBER_0, Base10Converter.convert(BASE_10_NUMBER_0, BASE_16));
    	Assert.assertEquals(BASE_16_NUMBER_256, Base10Converter.convert(BASE_10_NUMBER_256, BASE_16));
    	Assert.assertEquals(BASE_16_NUMBER_512, Base10Converter.convert(BASE_10_NUMBER_512, BASE_16));
    	Assert.assertEquals(BASE_16_NUMBER_MAX_INT, Base10Converter.convert(BASE_10_NUMBER_MAX_INT, BASE_16));
    }
    
    @Test
    public void convertToBase61() {
    	Assert.assertEquals(BASE_62_NUMBER_0, Base10Converter.convert(BASE_10_NUMBER_0, BASE_62));
    	Assert.assertEquals(BASE_62_NUMBER_256, Base10Converter.convert(BASE_10_NUMBER_256, BASE_62));
    	Assert.assertEquals(BASE_62_NUMBER_512, Base10Converter.convert(BASE_10_NUMBER_512, BASE_62));
    	Assert.assertEquals(BASE_62_NUMBER_MAX_INT, Base10Converter.convert(BASE_10_NUMBER_MAX_INT, BASE_62));
    }
    
}