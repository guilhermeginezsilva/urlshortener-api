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
	
	private static final String[] zeroTo100IdsBase62 = {"0000000", "0000001", "0000002", "0000003", "0000004", "0000005", "0000006", "0000007", "0000008", "0000009", "000000a", "000000b", "000000c", "000000d", "000000e", "000000f", "000000g", "000000h", "000000i", "000000j", "000000k", "000000l", "000000m", "000000n", "000000o", "000000p", "000000q", "000000r", "000000s", "000000t", "000000u", "000000v", "000000w", "000000x", "000000y", "000000z", "000000A", "000000B", "000000C", "000000D", "000000E", "000000F", "000000G", "000000H", "000000I", "000000J", "000000K", "000000L", "000000M", "000000N", "000000O", "000000P", "000000Q", "000000R", "000000S", "000000T", "000000U", "000000V", "000000W", "000000X", "000000Y", "000000Z", "0000010", "0000011", "0000012", "0000013", "0000014", "0000015", "0000016", "0000017", "0000018", "0000019", "000001a", "000001b", "000001c", "000001d", "000001e", "000001f", "000001g", "000001h", "000001i", "000001j", "000001k", "000001l", "000001m", "000001n", "000001o", "000001p", "000001q", "000001r", "000001s", "000001t", "000001u", "000001v", "000001w", "000001x", "000001y", "000001z", "000001A", "000001B"};

	@Autowired
	private IdGenerator idGenerator;
	
	@Test
    public void newIdGeneration() {
    	for(int i= 0; i < 100; i++) {
    		Assert.assertEquals(zeroTo100IdsBase62[i],idGenerator.getNewId().getGeneratedId());
    	}
    }
	
}
