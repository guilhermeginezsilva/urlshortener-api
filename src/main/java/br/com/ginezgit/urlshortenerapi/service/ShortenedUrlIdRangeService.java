package br.com.ginezgit.urlshortenerapi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ginezgit.urlshortenerapi.dao.ShortenedUrlIdRangeDAO;
import br.com.ginezgit.urlshortenerapi.model.db.ShortenedUrlIdRange;

@Service
public class ShortenedUrlIdRangeService {

	@Autowired
	private ShortenedUrlIdRangeDAO shortenedUrlIdRangeDAO;
	
	@Value("${application.instance.id:instance0}")
	private String applicationId;
	
	@Value("${id.range.size:1000}")
	private int rangeSize;
	
	@Transactional
	public ShortenedUrlIdRange getRange() {
		Optional<ShortenedUrlIdRange> recoveredShortenedUrlIdRange = shortenedUrlIdRangeDAO.findAvailableRangeByOwner(this.applicationId);
		if(!recoveredShortenedUrlIdRange.isPresent()) {
			return getNewRange();
		}
		return recoveredShortenedUrlIdRange.get();
	}
	
	@Transactional
	private ShortenedUrlIdRange getNewRange() {
		ShortenedUrlIdRange shortenedUrlIdRange;
		
		Optional<ShortenedUrlIdRange> recoveredShortenedUrlIdRangeOptional = shortenedUrlIdRangeDAO.getLastExistentRange();
		
		if(recoveredShortenedUrlIdRangeOptional.isPresent()) {
			ShortenedUrlIdRange recoveredShortenedUrlIdRange = recoveredShortenedUrlIdRangeOptional.get();
			int newRangeFirstId = recoveredShortenedUrlIdRange.getLastId() + 1;
			
			shortenedUrlIdRange = createNewRange(newRangeFirstId);
		} else {
			shortenedUrlIdRange = createFirstRange();
		}
		shortenedUrlIdRangeDAO.save(shortenedUrlIdRange);
		
		return shortenedUrlIdRange;
	}
	
	private ShortenedUrlIdRange createNewRange(int newRangeFirstId) {
		return new ShortenedUrlIdRange(newRangeFirstId, newRangeFirstId+this.rangeSize-1, this.applicationId, newRangeFirstId, new Date());
	}
	
	private ShortenedUrlIdRange createFirstRange() {
		return new ShortenedUrlIdRange(0, this.rangeSize-1, this.applicationId, 0, new Date());
	}
	
	public void updateRange(ShortenedUrlIdRange shortenedUrlIdRange) {
		shortenedUrlIdRange.setLastModified(new Date());
		shortenedUrlIdRangeDAO.save(shortenedUrlIdRange);
	}
	
}
