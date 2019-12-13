package co.grandcircus.bestgift.springmodelattributes;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

public class StringListModel {
	public List<String> strings = new LinkedList<>();

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}
}
