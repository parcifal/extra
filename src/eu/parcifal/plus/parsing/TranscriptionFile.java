package eu.parcifal.plus.parsing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptionFile extends File {

	private static final long serialVersionUID = 6029321665316406729L;

	private String raw;

	private Collection<TranscriptionSet> transcriptionSets;

	public TranscriptionFile(String pathname) {
		super(pathname);

		this.initialise();
	}

	private void initialise() {
		if (this.exists()) {
			try {
				this.raw = new String(Files.readAllBytes(this.toPath()), "UTF-8");

				Pattern pattern = Pattern.compile("([0-9a-zA-Z_\\-#.]+)\\s*\\{((?:[^\\}]|(?<=\\\\)\\})*)\\}");
				Matcher matcher = pattern.matcher(this.raw);

				this.transcriptionSets = new ArrayList<TranscriptionSet>();

				while (matcher.find()) {
					transcriptionSets.add(new TranscriptionSet(matcher.group(1), matcher.group(2)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Transcription get(String key, String... languages) {
		return this.get(key).get(languages);
	}

	public TranscriptionSet get(String key) {
		for (TranscriptionSet transcriptionSet : this.transcriptionSets) {
			if (transcriptionSet.key().equals(key)) {
				return transcriptionSet;
			}
		}

		throw new TranscriptionSetNotFoundException(key);
	}

}
