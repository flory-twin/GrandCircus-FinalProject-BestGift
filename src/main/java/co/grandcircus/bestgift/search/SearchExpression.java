package co.grandcircus.bestgift.search;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SearchExpression {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer searchId;
	
	@OneToOne
	private Keyword k1;
	@OneToOne
	private SearchExpression baseSE = null;
	@OneToOne
	private Keyword k2 = null;
	@Enumerated(EnumType.ORDINAL)
	private Operator o = null;

	// The following cases give a SearchExpression which can be evaluated.
	// Cases:
	// 1. Only Keyword k1 is set.
	// 2. Only Keyword k2 is set.
	// 3. Only SearchExpression baseSE is set.
	// 4. The Operator is set, and
	// 4.a. Both keywords are set.
	// 4.b. One keyword and the search expression are set.
	//
	// So that the search expression is always evaluatable, we must only allow
	// constructors which build SEs following those cases.
	// Of course, if we only allow a subset of those cases, the results are still
	// going to be evaluatable!!

	// Covers case 1.
	public SearchExpression(Keyword k) {
		super();
		this.k1 = k;
	}

	// Not covering case 2.

	// Covers case 3.
	public SearchExpression(SearchExpression se) {
		super();
		baseSE = se;
	}

	// Covers case 4a.
	public SearchExpression(Keyword k1, Operator o, Keyword k2) {
		this.k1 = k1;
		this.k2 = k2;
		this.o = o;
	}

	// Covers case 4b.
	public SearchExpression(Keyword k, Operator o, SearchExpression se) {
		this.k1 = k;
		this.o = o;
		this.baseSE = se;
	}

	// Allow access to members.
	public Keyword getKeyword1() {
		return k1;
	}

	public SearchExpression getBaseExpression() {
		return baseSE;
	}

	public Keyword getKeyword2() {
		return k2;
	}

	public Operator getOperator() {
		return o;
	}

	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	public Keyword getK1() {
		return k1;
	}

	public void setK1(Keyword k1) {
		this.k1 = k1;
	}

	public SearchExpression getBaseSE() {
		return baseSE;
	}

	public void setBaseSE(SearchExpression baseSE) {
		this.baseSE = baseSE;
	}

	public Keyword getK2() {
		return k2;
	}

	public void setK2(Keyword k2) {
		this.k2 = k2;
	}

	public Operator getO() {
		return o;
	}

	public void setO(Operator o) {
		this.o = o;
	}

}
