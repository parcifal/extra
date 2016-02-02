package eu.parcifal.plus.parsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptionSet {

	private Collection<Transcription> transcriptions;

	private String key;
	private String raw;

	public TranscriptionSet(String key, String raw) {
		this.key = key;
		this.raw = raw;
		this.initialise();
	}

	private void initialise() {
		Pattern pattern = Pattern.compile("\\s*((?:[^:]|(?<=\\\\):)+)\\s*:\\s*\"((?:[^\"]|(?<=\\\\)\")*)\"");
		Matcher matcher = pattern.matcher(this.raw);

		this.transcriptions = new ArrayList<Transcription>();

		while (matcher.find()) {
			transcriptions.add(new Transcription(matcher.group(1), matcher.group(2)));
		}
	}

	public String key() {
		return this.key;
	}

	public Transcription get(String... languages) {
		for (Transcription transcription : this.transcriptions) {
			for (String language : languages) {
				if (Pattern.matches(transcription.language(), language)) {
					return transcription;
				}
			}
		}

		throw new LanguageNotFoundException(languages);
	}

}
