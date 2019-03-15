package br.com.ginezgit.urlshortenerapi.id;

import org.junit.Assert;
import org.junit.Test;

import br.com.ginezgit.urlshortenerapi.id.IdGenerator;
import br.com.ginezgit.urlshortenerapi.id.IdGeneratorImpl;

public class IdGeneratorImplTest {
	
	private static final String[] zeroTo100IdsBase16 = {"0000000", "0000001", "0000002", "0000003", "0000004", "0000005", "0000006", "0000007", "0000008", "0000009", "000000a", "000000b", "000000c", "000000d", "000000e", "000000f", "0000010", "0000011", "0000012", "0000013", "0000014", "0000015", "0000016", "0000017", "0000018", "0000019", "000001a", "000001b", "000001c", "000001d", "000001e", "000001f", "0000020", "0000021", "0000022", "0000023", "0000024", "0000025", "0000026", "0000027", "0000028", "0000029", "000002a", "000002b", "000002c", "000002d", "000002e", "000002f", "0000030", "0000031", "0000032", "0000033", "0000034", "0000035", "0000036", "0000037", "0000038", "0000039", "000003a", "000003b", "000003c", "000003d", "000003e", "000003f", "0000040", "0000041", "0000042", "0000043", "0000044", "0000045", "0000046", "0000047", "0000048", "0000049", "000004a", "000004b", "000004c", "000004d", "000004e", "000004f", "0000050", "0000051", "0000052", "0000053", "0000054", "0000055", "0000056", "0000057", "0000058", "0000059", "000005a", "000005b", "000005c", "000005d", "000005e", "000005f", "0000060", "0000061", "0000062", "0000063"};
	private static final String[] zeroTo100IdsBase62 = {"0000000", "0000001", "0000002", "0000003", "0000004", "0000005", "0000006", "0000007", "0000008", "0000009", "000000a", "000000b", "000000c", "000000d", "000000e", "000000f", "000000g", "000000h", "000000i", "000000j", "000000k", "000000l", "000000m", "000000n", "000000o", "000000p", "000000q", "000000r", "000000s", "000000t", "000000u", "000000v", "000000w", "000000x", "000000y", "000000z", "000000A", "000000B", "000000C", "000000D", "000000E", "000000F", "000000G", "000000H", "000000I", "000000J", "000000K", "000000L", "000000M", "000000N", "000000O", "000000P", "000000Q", "000000R", "000000S", "000000T", "000000U", "000000V", "000000W", "000000X", "000000Y", "000000Z", "0000010", "0000011", "0000012", "0000013", "0000014", "0000015", "0000016", "0000017", "0000018", "0000019", "000001a", "000001b", "000001c", "000001d", "000001e", "000001f", "000001g", "000001h", "000001i", "000001j", "000001k", "000001l", "000001m", "000001n", "000001o", "000001p", "000001q", "000001r", "000001s", "000001t", "000001u", "000001v", "000001w", "000001x", "000001y", "000001z", "000001A", "000001B"};

	@Test
    public void newIdGeneration() {
		IdGenerator idGenerator = new IdGeneratorImpl();
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase62[i],idGenerator.getNewId());
    	}
    }
	
	@Test
    public void newIdGenerationAsBase10() {
		IdGeneratorImpl idGenerator = new IdGeneratorImpl();
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(String.format("%0"+IdGeneratorImpl.DEFAULT_ID_LENGTH+"d" , i), idGenerator.getNewIdOnBase(10));
    	}
    }
	
	@Test
    public void newIdGenerationAsBase16() {
		IdGeneratorImpl idGenerator = new IdGeneratorImpl();
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase16[i],idGenerator.getNewIdOnBase(16));
    	}
    }
	
	@Test
    public void newIdGenerationAsBase62() {
		IdGeneratorImpl idGenerator = new IdGeneratorImpl();
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase62[i],idGenerator.getNewIdOnBase62());
    	}
    }

}
