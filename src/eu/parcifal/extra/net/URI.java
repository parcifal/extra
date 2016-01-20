package eu.parcifal.extra.net;

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
	/**
	 * The raw representation of the current URI.
	 */
	private String raw;

	/**
	 * Construct a new URI from a string.
	 * 
	 * @param raw
	 *            The raw representation of the current URI.
	 */
	public URI(String raw) {
		this.raw = raw;
	}

	/**
	 * Return the scheme of the current URI or an empty string if no scheme is
	 * defined.
	 * 
	 * @return The scheme of the current URI or an empty string if no scheme is
	 *         defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.1
	 */
	public final String scheme() {
		Pattern pattern = Pattern.compile("^(?:([A-Za-z][A-Za-z0-9+.-]*):\\/\\/)?");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the authority of the current URI or an empty string if no
	 * authority is defined.
	 * 
	 * @return The authority of the current URI.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.2
	 */
	public final String authority() {
		Pattern pattern = Pattern
				.compile("(?:((?:#[0-9A-Fa-f]{2}|[!$&'\\(\\)*+,;=A-Za-z0-9-._~])*)@)([^\\/:]+)(?::([0-9]*))?");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(0);
		} else {
			return "";
		}
	}

	/**
	 * Return the user information of the authority of the current URI or an
	 * empty string if no authority is defined.
	 * 
	 * @return The user information of the authority of the current URI or an
	 *         empty string if no authority is defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.2.1
	 */
	public final String userinfo() {
		Pattern pattern = Pattern.compile("^((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:]|%[0-9A-Fa-f]{2})*)@");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the host of the authority of the current URI or an empty string if
	 * no authority is defined.
	 * 
	 * @return The host of the authority of the current URI or an empty string
	 *         if no authority is defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.2.2
	 */
	public final String host() {
		Pattern pattern = Pattern.compile("(?:@|^)((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=]|%[0-9A-Fa-f]{2})*)(?::|$)");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the port of the authority of the current URI or an empty string if
	 * no port is defined.
	 * 
	 * @return The port of the authority of the current URI or an empty string
	 *         if no port is defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.2.3
	 */
	public final String port() {
		Pattern pattern = Pattern.compile(":([0-9]*)$");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the path of the current URI or an empty string if no path is
	 * defined.
	 * 
	 * @return The path of the current URI or an empty string if no path is
	 *         defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.3
	 */
	public final String path() {
		Pattern pattern = Pattern.compile("(\\/(?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@]|%[0-9A-Fa-f]{2})*)(?:\\?|#|$)");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the query of the current URI or an empty string if no query is
	 * defined.
	 * 
	 * @return The query of the current URI or an empty string if no query is
	 *         defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.4
	 */
	public final String query() {
		Pattern pattern = Pattern.compile("\\?((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@\\/?]|%[0-9A-Fa-f]{2})*)(?:#|$)");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the fragment of the current URI or an empty string if no fragment
	 * is defined.
	 * 
	 * @return The fragment of the current URI or an empty string if no query is
	 *         defined.
	 * @see https://tools.ietf.org/html/rfc3986#section-3.5
	 */
	public final String fragment() {
		Pattern pattern = Pattern.compile("#((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@\\/?]|%[0-9A-Fa-f]{2})*)$");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	/**
	 * Return the raw value of the current URI.
	 */
	@Override
	public String toString() {
		return this.raw;
	}

}
