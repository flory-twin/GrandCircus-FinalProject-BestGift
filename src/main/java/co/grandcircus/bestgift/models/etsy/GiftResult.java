package co.grandcircus.bestgift.models.etsy;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public class GiftResult {
	private int count;
	ArrayList<Gift> results;

	public GiftResult() {
		super();
	}

	public GiftResult(int count, ArrayList<Gift> results) {
		super();
		this.count = count;
		this.results = results;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Gift> getResults() {
		return results;
	}

	public void setResults(ArrayList<Gift> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "GiftResult [count=" + count + ", results=" + results + "]";
	}
}
