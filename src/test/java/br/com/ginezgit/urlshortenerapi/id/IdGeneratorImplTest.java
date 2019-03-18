package br.com.ginezgit.urlshortenerapi.id;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IdGeneratorImplTest {
	
	private static final String[] zeroTo100IdsBase62 = {"00000", "00001", "00002", "00003", "00004", "00005", "00006", "00007", "00008", "00009", "0000a", "0000b", "0000c", "0000d", "0000e", "0000f", "0000g", "0000h", "0000i", "0000j", "0000k", "0000l", "0000m", "0000n", "0000o", "0000p", "0000q", "0000r", "0000s", "0000t", "0000u", "0000v", "0000w", "0000x", "0000y", "0000z", "0000A", "0000B", "0000C", "0000D", "0000E", "0000F", "0000G", "0000H", "0000I", "0000J", "0000K", "0000L", "0000M", "0000N", "0000O", "0000P", "0000Q", "0000R", "0000S", "0000T", "0000U", "0000V", "0000W", "0000X", "0000Y", "0000Z", "00010", "00011", "00012", "00013", "00014", "00015", "00016", "00017", "00018", "00019", "0001a", "0001b", "0001c", "0001d", "0001e", "0001f", "0001g", "0001h", "0001i", "0001j", "0001k", "0001l", "0001m", "0001n", "0001o", "0001p", "0001q", "0001r", "0001s", "0001t", "0001u", "0001v", "0001w", "0001x", "0001y", "0001z", "0001A", "0001B"};

	@Autowired
	private IdGenerator idGenerator;
	
	@Test
    public void Should_GenerateIdSequence_Always() {
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase62[i],idGenerator.getNewId().getGeneratedId());
    	}
    }
	
}
