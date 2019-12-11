package co.grandcircus.bestgift.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.search.SearchExpression;

public interface SearchExpressionRepository extends JpaRepository<SearchExpression, Integer> {

}
