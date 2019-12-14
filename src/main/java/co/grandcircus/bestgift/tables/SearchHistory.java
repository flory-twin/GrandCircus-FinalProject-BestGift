package co.grandcircus.bestgift.tables;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import co.grandcircus.bestgift.models.Gift;
import co.grandcircus.bestgift.search.SearchExpression;

@Entity
public class SearchHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_log_id")
	private Integer historyLogId;
	
	@OneToOne
	private SearchExpression query;
	@OneToOne
	private GiftList searchResult;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
//	
//	DateTimeFormatter dt = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
//	
//	String formatedtime = lt.format(dt);
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public Integer getHistoryLogId() {
		return historyLogId;
	}
	public void setHistoryLogId(Integer historyLogId) {
		this.historyLogId = historyLogId;
	}
	public SearchExpression getQuery() {
		return query;
	}
	public void setQuery(SearchExpression query) {
		this.query = query;
	}
	public GiftList getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(GiftList searchResult) {
		this.searchResult = searchResult;
	}
	public SearchHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchHistory(SearchExpression query, List<Gift> searchResult) {
		this();
		this.query = query;
		this.searchResult = new GiftList(searchResult);
	}
	public SearchHistory(Integer historyLogId, SearchExpression query, List<Gift> searchResult) {
		this(query, searchResult);
		this.historyLogId = historyLogId;
	}
	
	public SearchHistory(SearchExpression query, GiftList searchResult) {
		super();
		this.query = query;
		this.searchResult = searchResult;
	}
	public SearchHistory(Integer historyLogId, SearchExpression query, GiftList searchResult) {
		super();
		this.historyLogId = historyLogId;
		this.query = query;
		this.searchResult = searchResult;
	}
	
}
