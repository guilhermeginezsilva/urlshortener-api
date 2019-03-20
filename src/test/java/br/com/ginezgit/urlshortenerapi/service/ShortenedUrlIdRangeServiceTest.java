package br.com.ginezgit.urlshortenerapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ginezgit.urlshortenerapi.dao.ShortenedUrlIdRangeDAO;
import br.com.ginezgit.urlshortenerapi.model.db.ShortenedUrlIdRange;

@RunWith(SpringRunner.class)
public class ShortenedUrlIdRangeServiceTest {

	@TestConfiguration
    static class ShortenedUrlIdRangeServiceTestContextConfiguration {
  
        @Bean
        public ShortenedUrlIdRangeService shortenedUrlIdRangeService() {
            return new ShortenedUrlIdRangeService();
        }
    }
 
    @Autowired
    private ShortenedUrlIdRangeService shortenedUrlIdRangeService;
    
    @Value("${id.range.size:1000}")
	private int rangeSize;
 
    @MockBean
    private ShortenedUrlIdRangeDAO shortenedUrlIdRangeDAO;
	
    @Before
    public void init() {
    }
    
	@Test
	public void Should_GetFirstRange_When_ThereAreNoRanges() {
		Mockito.doReturn(null).when(shortenedUrlIdRangeDAO).save(any(ShortenedUrlIdRange.class));
		
		ShortenedUrlIdRange range = shortenedUrlIdRangeService.getRange();
		assertEquals(range.getFirstId(), new Integer(0));
		assertEquals(range.getLastId(), new Integer(rangeSize-1));
	}
	
	@Test
	public void Should_GetExistentRange_When_OwnersLastRangeHasntEnded() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(1000, 1999, "", 1000, new Date());
		Mockito.doReturn(Optional.of(range)).when(shortenedUrlIdRangeDAO).findAvailableRangeByOwner(any(String.class));
		
		ShortenedUrlIdRange foundRange = shortenedUrlIdRangeService.getRange();
		assertEquals(range.getFirstId(), foundRange.getFirstId());
		assertEquals(range.getLastId(), foundRange.getLastId());
	}
	
	@Test
	public void Should_GetNewRange_When_OwnersLastRangeHasEnded() {
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(1000, 1999, "", 2000, new Date());
		Mockito.doReturn(Optional.empty()).when(shortenedUrlIdRangeDAO).findAvailableRangeByOwner(any(String.class));
		Mockito.doReturn(Optional.of(range)).when(shortenedUrlIdRangeDAO).getLastExistentRange();
		
		ShortenedUrlIdRange foundRange = shortenedUrlIdRangeService.getRange();
		assertEquals(new Integer(range.getLastId()+1), foundRange.getFirstId());
	}
	
	@Test
	public void Should_DateWasUpdated_When_UpdatedRange() throws InterruptedException {
		Mockito.doReturn(null).when(shortenedUrlIdRangeDAO).save(any(ShortenedUrlIdRange.class));
		
		Date firstDate = new Date();
		ShortenedUrlIdRange range = new ShortenedUrlIdRange(1000, 1999, "", 2000, firstDate);
		
		Thread.sleep(1);
		shortenedUrlIdRangeService.updateRange(range);
		assertTrue(firstDate.before(range.getLastModified()));
	}
	
}
