package co.grandcircus.bestgift.models;

import java.util.List;

public class SynonymResponse {
	
	private List<Word> words;

	public SynonymResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SynonymResponse(List<Word> words) {
		super();
		this.words = words;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}
