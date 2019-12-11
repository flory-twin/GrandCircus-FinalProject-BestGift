package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.tables.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

}
