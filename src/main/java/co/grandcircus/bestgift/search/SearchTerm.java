package co.grandcircus.bestgift.search;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SearchTerm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String value;

	public SearchTerm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchTerm(String value) {
		super();
		this.value = value;
	}

	public SearchTerm(Integer id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public String getUrlEncodedValue() {
		return value.replaceAll(" ", "+");
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Keyword [id=" + id + ", value=" + value + "]";
	}

}
