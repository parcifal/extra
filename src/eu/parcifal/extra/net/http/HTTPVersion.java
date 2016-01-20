package eu.parcifal.extra.net.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPVersion {

	private final static String STRING_FORMAT = "HTTP/%1$s.%2$s";

	private int major;

	private int minor;

	public HTTPVersion(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	public final int major() {
		return this.major;
	}

	public final int minor() {
		return this.minor;
	}

	public final static HTTPVersion parse(String httpVersion) {
		Pattern pattern = Pattern.compile("HTTP\\/(\\d)\\.(\\d)");
		Matcher matcher = pattern.matcher(httpVersion);

		if (matcher.find()) {
			int major = Integer.parseInt(matcher.group(1));
			int minor = Integer.parseInt(matcher.group(2));

			return new HTTPVersion(major, minor);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return String.format(STRING_FORMAT, this.major, this.minor);
	}

}
