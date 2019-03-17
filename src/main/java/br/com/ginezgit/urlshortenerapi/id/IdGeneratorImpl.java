package br.com.ginezgit.urlshortenerapi.id;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.ginezgit.urlshortenerapi.model.db.ShortenedUrlIdRange;
import br.com.ginezgit.urlshortenerapi.service.ShortenedUrlIdRangeService;
import br.com.ginezgit.urlshortenerapi.util.Base10Converter;
import br.com.ginezgit.urlshortenerapi.util.StringUtils;
import br.com.ginezgit.urlshortenerapi.util.StringUtils.Side;

@Component
@Scope("application")
public class IdGeneratorImpl implements IdGenerator {

	@Autowired
	private ShortenedUrlIdRangeService shortenedUrlIdRangeService;
	private ShortenedUrlIdRange idRange;
	
	public static final int DEFAULT_ID_LENGTH = 7;
	public static final char DEFAULT_ID_PADDING_CHAR = '0';
	
	@PostConstruct
	public void init() {
		getRangeFromDatabase();
	}
	
	private void getRangeFromDatabase() {
		this.idRange = this.shortenedUrlIdRangeService.getRange();
	}
	
	@Override
	public GeneratedId getNewId() {
		synchronized (idRange) {
			int newId = getNewIdCounter();
			if(this.idRange.hasEnded()) {
				getRangeFromDatabase();
			}
			return new GeneratedId(getIdOnBase62(newId), newId);
		}
	}
	
	private int getNewIdCounter() {
		int newId = this.idRange.nextId();
		this.shortenedUrlIdRangeService.updateRange(idRange);
		return newId;
	}
	
	private synchronized String getIdOnBase62(int newId) {
		synchronized (idRange) {
			return addPadding(Base10Converter.convert(newId, 62));
		}
	}
	
	private String addPadding(String value) {
		return StringUtils.padding(value, DEFAULT_ID_LENGTH, DEFAULT_ID_PADDING_CHAR, Side.LEFT);
	}


	
}
