package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.search.SearchTerm;

public interface SearchTermRepository extends JpaRepository<SearchTerm, Integer> {

}
