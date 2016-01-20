package eu.parcifal.extra.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URI {

	private String uri;

	public URI(String uri) {
		this.uri = uri;
	}

	public final String scheme() {
		Pattern pattern = Pattern.compile("^(?:([A-Za-z][A-Za-z0-9+.-]*):\\/\\/)?");
		Matcher matcher = pattern.matcher(this.uri);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String authority() {
		Pattern pattern = Pattern
				.compile("(?:((?:#[0-9A-Fa-f]{2}|[!$&'\\(\\)*+,;=A-Za-z0-9-._~])*)@)([^\\/:]+)(?::([0-9]*))?");
		Matcher matcher = pattern.matcher(this.uri);

		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return this.uri;
	}

}
