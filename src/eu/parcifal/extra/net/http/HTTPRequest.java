package eu.parcifal.extra.net.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.parcifal.extra.net.URI;
import eu.parcifal.extra.print.Console;

public class HTTPRequest extends HTTPExchange {

	private final static String PATTERN_REQUEST = "^(OPTIONS|GET|HEAD|POST|PUT|DELETE|TRACE|CONNECT) ((?:[A-Za-z\\d:/?#\\[\\]@!$&'\\(\\)*+,;=\\-._~]|%[0-9A-Fa-f]{2})*) (HTTP\\/[\\d]\\.[\\d])$";

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
		Console.log(httpRequest);

		Pattern pattern = Pattern.compile(PATTERN_REQUEST, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(httpRequest);

		if (matcher.find()) {
			Method method = Method.valueOf(matcher.group(1));
			URI uri = new URI(matcher.group(2));
			HTTPVersion version = HTTPVersion.parse(matcher.group(3));

			return new HTTPRequest(method, uri, version, httpRequest);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public enum Method {
		OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
	}

}
