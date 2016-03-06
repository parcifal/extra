package eu.parcifal.plus.parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
			InputStream stream = null;

			try {
				byte[] content = new byte[(int) this.length()];

				stream = new FileInputStream(this);

				stream.read(content);

				this.raw = new String(content);

				Pattern pattern = Pattern.compile("([^\\{\\s]*)\\s*\\{\\r?\\n((?:[^:\\r\\n]*: \"[^\"]*\"\\r?\\n)*)\\}");
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
