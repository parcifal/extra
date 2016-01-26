package eu.parcifal.extra.net.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.parcifal.extra.net.URI;

public class HTTPRequest extends HTTPExchange {

	private final static String PATTERN_REQUEST = "^(OPTIONS|GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT) ((?:[0-9A-Za-z:/?#\\[\\]@!$&'\\(\\)*+,;=\\-._~]|%[0-9A-Fa-f]{2})*) (HTTP\\/[0-9]\\.[0-9])$";

	private final static String PATTERN_HEADER = "^([^:\\s]+): (.+)$";

	private Method method;
	private URI uri;
	private HTTPVersion version;
	private String raw;

	public HTTPRequest(Method method, URI uri, HTTPVersion version, String raw) {
		this.method = method;
		this.uri = uri;
		this.version = version;
		this.raw = raw;
	}

	public final Method method() {
		return this.method;
	}

	public final URI uri() {
		return this.uri;
	}

	public final HTTPVersion version() {
		return this.version;
	}

	public final String raw() {
		return this.raw;
	}

	public static HTTPRequest parse(String httpRequest) {
		Pattern pattern = Pattern.compile(PATTERN_REQUEST, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(httpRequest);

		if (matcher.find()) {
			Method method = Method.valueOf(matcher.group(1));
			URI uri = new URI(matcher.group(2));
			HTTPVersion version = HTTPVersion.parse(matcher.group(3));

			HTTPRequest request = new HTTPRequest(method, uri, version, httpRequest);

			Pattern patternHeader = Pattern.compile(PATTERN_HEADER, Pattern.MULTILINE);
			Matcher matcherHeader = patternHeader.matcher(httpRequest);

			while (matcherHeader.find()) {
				request.header(matcherHeader.group(1), matcherHeader.group(2));
			}

			return request;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public enum Method {
		OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
	}

}
