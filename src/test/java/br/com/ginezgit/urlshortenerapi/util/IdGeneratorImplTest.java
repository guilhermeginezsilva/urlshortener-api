package br.com.ginezgit.urlshortenerapi.util;

import org.junit.Assert;
import org.junit.Test;

public class IdGeneratorImplTest {

	@Test
    public void newIdGenerationAsIntStartingOn0() {
    	IdGenerator idGenerator = new IdGeneratorImpl();
    	for(int i =0; i < 10; i++) {
    		Assert.assertEquals(i,idGenerator.getNewId());
    	}
    }
	
	@Test
    public void newIdGenerationAsBase16() {
		
		String[] zeroTo100IdsBase16 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c", "5d", "5e", "5f", "60", "61", "62", "63"};
		
    	IdGenerator idGenerator = new IdGeneratorImpl();
    	for(int i =0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase16[i],idGenerator.getNewIdOnBase(16));
    	}
    }
	
	@Test
    public void newIdGenerationAsBase62() {
		
		String[] zeroTo100IdsBase62 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "1g", "1h", "1i", "1j", "1k", "1l", "1m", "1n", "1o", "1p", "1q", "1r", "1s", "1t", "1u", "1v", "1w", "1x", "1y", "1z", "1A", "1B"};
		
    	IdGenerator idGenerator = new IdGeneratorImpl();
    	for(int i =0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase62[i],idGenerator.getNewIdOnBase62());
    	}
    }

}
