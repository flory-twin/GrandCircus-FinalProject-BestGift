package co.grandcircus.bestgift.jparepos;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.bestgift.tables.GiftList;
import co.grandcircus.bestgift.tables.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
	@Query("SELECT sh FROM SearchHistory sh WHERE sh.lt = (SELECT max(m.lt) FROM SearchHistory m)")	
	public SearchHistory findByMaxCreatedAt();
	
	@Query("SELECT MAX(m.lt) FROM SearchHistory m")	
	public LocalDateTime findByAllCreatedAt();
	
	public SearchHistory findBySearchResult(GiftList gl);
}
