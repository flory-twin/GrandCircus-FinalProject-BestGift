package co.grandcircus.bestgift.models.datamuse;

import java.util.List;

public class SynonymResponse {
	
	private List<Word> words;

	public SynonymResponse() {
		super();
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
