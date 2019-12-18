package co.grandcircus.bestgift.jparepos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.bestgift.models.User;
import co.grandcircus.bestgift.tables.GiftList;
import co.grandcircus.bestgift.tables.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
	@Query("SELECT sh FROM SearchHistory sh WHERE sh.createdAt = (SELECT max(m.createdAt) FROM SearchHistory m)")	
	public SearchHistory findByMaxCreatedAt();
	
	public SearchHistory findBySearchResult(GiftList gl);
	
	@Query("SELECT sh FROM SearchHistory sh WHERE user = ?1")
	List<SearchHistory> findSearchByUser(User user);
	
}
