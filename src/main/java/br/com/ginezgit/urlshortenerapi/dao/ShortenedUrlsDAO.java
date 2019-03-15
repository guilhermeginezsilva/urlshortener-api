package br.com.ginezgit.urlshortenerapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ginezgit.urlshortenerapi.model.db.Url;

@Repository
public interface ShortenedUrlsDAO extends CrudRepository<Url, String>{

	 @Query("SELECT url FROM Url url WHERE url.originalUrl=:originalUrl")
	 List<Url> findAllByOriginalUrl(@Param("originalUrl") String originalUrl, Pageable pageable);
	 
	 default Optional<Url> findByOriginalUrl(String originalUrl) {
		return findAllByOriginalUrl(originalUrl, PageRequest.of(0, 1)).stream().findFirst();
	}
	
}
