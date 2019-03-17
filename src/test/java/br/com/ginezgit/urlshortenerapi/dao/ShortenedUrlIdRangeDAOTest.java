package br.com.ginezgit.urlshortenerapi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ginezgit.urlshortenerapi.model.db.ShortenedUrlIdRange;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShortenedUrlIdRangeDAOTest {

	@Autowired
    private ShortenedUrlIdRangeDAO shortenedUrlIdRangeDAO;
	
	private String range1Owner = "instance1";
	private ShortenedUrlIdRange range1 = new ShortenedUrlIdRange(0, 1, range1Owner, 0, new Date());
	private String range2Owner = "instance2";
	private ShortenedUrlIdRange range2 = new ShortenedUrlIdRange(2, 3, range2Owner, 2, new Date());
	
	private String noRangeOwner = "instance3";
	
	@After
	public void afterTests() {
		shortenedUrlIdRangeDAO.deleteAll();
	}
	
	@Test
	public void Should_FindAvailableRange_When_SearchByOwnerAndExists() {
		shortenedUrlIdRangeDAO.save(range1);
		shortenedUrlIdRangeDAO.save(range2);
		
		Optional<ShortenedUrlIdRange> found = shortenedUrlIdRangeDAO.findAvailableRangeByOwner(range1Owner);
	 
	    assertTrue(found.isPresent());
	    assertEquals(range1.getFirstId(), found.get().getFirstId());
	    assertEquals(range1.getLastId(), found.get().getLastId());
	    
	    Optional<ShortenedUrlIdRange> found2 = shortenedUrlIdRangeDAO.findAvailableRangeByOwner(range2Owner);
		 
	    assertTrue(found2.isPresent());
	    assertEquals(range2.getFirstId(), found2.get().getFirstId());
	    assertEquals(range2.getLastId(), found2.get().getLastId());
	}
	
	@Test
	public void Should_DontFindAvailableRange_When_SearchByOwnerAndDontExists() {
		Optional<ShortenedUrlIdRange> found = shortenedUrlIdRangeDAO.findAvailableRangeByOwner(noRangeOwner);
	    assertFalse(found.isPresent());
	}
	
	@Test
	public void Should_DontReturnLastExistentRange_When_ThereIsNoRange() {
		Optional<ShortenedUrlIdRange> found = shortenedUrlIdRangeDAO.getLastExistentRange();
	    assertTrue(!found.isPresent());
	}
	
	@Test
	public void Should_ReturnLastExistentRange_When_ThereIsRange() {
		shortenedUrlIdRangeDAO.save(range1);
		
		Optional<ShortenedUrlIdRange> found = shortenedUrlIdRangeDAO.getLastExistentRange();
	 
	    assertTrue(found.isPresent());
	    assertEquals(range1.getFirstId(), found.get().getFirstId());
	    assertEquals(range1.getLastId(), found.get().getLastId());
	}
	
	@Test
	public void Should_ReturnLastExistentRange_When_ThereAreRanges() {
		shortenedUrlIdRangeDAO.save(range1);
		shortenedUrlIdRangeDAO.save(range2);
		
		Optional<ShortenedUrlIdRange> found = shortenedUrlIdRangeDAO.getLastExistentRange();
	 
	    assertTrue(found.isPresent());
	    assertEquals(range2.getFirstId(), found.get().getFirstId());
	    assertEquals(range2.getLastId(), found.get().getLastId());
	}
	
}
