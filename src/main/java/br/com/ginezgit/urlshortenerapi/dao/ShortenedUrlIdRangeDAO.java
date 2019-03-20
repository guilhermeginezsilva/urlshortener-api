package br.com.ginezgit.urlshortenerapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ginezgit.urlshortenerapi.model.db.ShortenedUrlIdRange;

@Repository
public interface ShortenedUrlIdRangeDAO extends CrudRepository<ShortenedUrlIdRange, Integer>{

	@Query("SELECT range FROM ShortenedUrlIdRange range WHERE range.currentId <= range.lastId AND owner=:applicationId")
	List<ShortenedUrlIdRange> findAllAvailableRangeByOwner(String applicationId, Pageable pageable);

	default Optional<ShortenedUrlIdRange> findAvailableRangeByOwner(String applicationId) {
		return findAllAvailableRangeByOwner(applicationId, PageRequest.of(0, 1)).stream().findFirst();
	}
	
	@Query("SELECT range FROM ShortenedUrlIdRange range ORDER BY range.id DESC")
	List<ShortenedUrlIdRange> getAllDesc(Pageable pageable);
	
	default Optional<ShortenedUrlIdRange> getLastExistentRange() {
		return getAllDesc(PageRequest.of(0, 1)).stream().findFirst();
	}

}
