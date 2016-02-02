package eu.parcifal.plus.parsing;

public class Transcription {

	private String language;
	private String text;

	public Transcription(String language, String text) {
		this.language = language;
		this.text = text;
	}

	public String language() {
		return this.language;
	}

	public String text() {
		return this.text;
	}

}
