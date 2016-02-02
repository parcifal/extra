package eu.parcifal.plus.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the Uniform Resource Identifier (URI) format as defined
 * by The Internet Society.
 * 
 * @author Michaël van de Weerd
 * @see https://tools.ietf.org/html/rfc3986
 */
public class URI {
	private final static String STRING_FORMAT_SCHEME = "%1$s:";
	private final static String STRING_FORMAT_AUTHORITY = "//%1$s";
	private final static String STRING_FORMAT_USERINFO = "%1$s@";
	private final static String STRING_FORMAT_HOST = "%1$s";
	private final static String STRING_FORMAT_PORT = ":%1$d";
	private final static String STRING_FORMAT_PATH = "%1$s";
	private final static String STRING_FORMAT_QUERY = "?%1$s";
	private final static String STRING_FORMAT_FRAGMENT = "#%1$s";

	private String scheme;
	private String userinfo;
	private String host;
	private int port;
	private String path;
	private String query;
	private String fragment;

	public URI(String scheme, String userinfo, String host, int port, String path, String query, String fragment) {
		this.scheme = scheme;
		this.userinfo = userinfo;
		this.host = host;
		this.port = port;
		this.path = path;
		this.query = query;
		this.fragment = fragment;
	}

	public String scheme() {
		return this.scheme;
	}

	public String authority() {
		String authority = "";

		if (!(this.userinfo == null || this.userinfo.isEmpty())) {
			authority += String.format(STRING_FORMAT_USERINFO, this.userinfo);
		}

		if (!(this.host == null || this.host.isEmpty())) {
			authority += String.format(STRING_FORMAT_HOST, this.host);
		}

		if (this.port != -1) {
			authority += String.format(STRING_FORMAT_PORT, this.port);
		}

		return authority;
	}

	public String userinfo() {
		return this.userinfo;
	}

	public String host() {
		return this.host;
	}

	public int port() {
		return this.port;
	}

	public String path() {
		return this.path;
	}

	public String query() {
		return this.query;
	}

	public String fragment() {
		return this.fragment;
	}

	public static URI fromString(String raw) {
		Pattern pattern = Pattern.compile(
				"^(?:([a-zA-Z][a-zA-Z0-9+\\-.]*):)?(?:\\/{2}(?:((?:[a-zA-z0-9\\-._~]|(?:%[a-fA-F0-9]{2})|[!$&'()*+,;=]|:)*)@)?((?:[a-zA-Z0-9\\-._~]|(?:%[a-fA-F0-9]{2})|[!$&'()*+,;=])*)(:[0-9]*)?)?(?:((?:\\/(?:[a-zA-Z0-9\\-._~]|(?:%[a-fA-F0-9]{2})|[!$&'()*+,;=]|:|@)*)*))?(?:\\?((?:[a-zA-Z0-9\\-._~]|(?:%[a-fA-F0-9]{2})|[!$&'()*+,;=]|:|@|\\\\|\\?)*))?(?:#((?:[a-zA-Z0-9\\-._~]|(?:%[a-fA-F0-9]{2})|[!$&'()*+,;=]|:|@|\\\\|\\?)*))?");
		Matcher matcher = pattern.matcher(raw);

		if (matcher.find()) {
			String scheme = matcher.group(1);
			String userinfo = matcher.group(2);
			String host = matcher.group(3);
			int port = matcher.group(4) == null ? -1 : Integer.valueOf(matcher.group(4));
			String path = matcher.group(5);
			String query = matcher.group(6);
			String fragment = matcher.group(7);

			return new URI(scheme, userinfo, host, port, path, query, fragment);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		String uri = "";

		if (!(this.scheme == null || this.scheme.isEmpty())) {
			uri += String.format(STRING_FORMAT_SCHEME, this.scheme);
		}

		if (!this.authority().isEmpty()) {
			uri += String.format(STRING_FORMAT_AUTHORITY, this.authority());
		}

		if (!(this.path == null || this.path.isEmpty())) {
			uri += String.format(STRING_FORMAT_PATH, this.path);
		}

		if (!(this.query == null || this.query.isEmpty())) {
			uri += String.format(STRING_FORMAT_QUERY, this.query);
		}

		if (!(this.fragment == null || this.fragment.isEmpty())) {
			uri += String.format(STRING_FORMAT_FRAGMENT, this.fragment);
		}

		return uri;
	}

}
