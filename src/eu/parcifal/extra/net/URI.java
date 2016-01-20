package eu.parcifal.extra.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URI {

	private String raw;

	public URI(String raw) {
		this.raw = raw;
	}

	public final String scheme() {
		Pattern pattern = Pattern.compile("^(?:([A-Za-z][A-Za-z0-9+.-]*):\\/\\/)?");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

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

	public final String userinfo() {
		Pattern pattern = Pattern.compile("^((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:]|%[0-9A-Fa-f]{2})*)@");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String host() {
		Pattern pattern = Pattern.compile("(?:@|^)((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=]|%[0-9A-Fa-f]{2})*)(?::|$)");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String port() {
		Pattern pattern = Pattern.compile(":([0-9]*)$");
		Matcher matcher = pattern.matcher(this.authority());

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String path() {
		Pattern pattern = Pattern.compile("(\\/(?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@]|%[0-9A-Fa-f]{2})*)(?:\\?|#|$)");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String query() {
		Pattern pattern = Pattern.compile("\\?((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@\\/?]|%[0-9A-Fa-f]{2})*)(?:#|$)");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	public final String fragment() {
		Pattern pattern = Pattern.compile("#((?:[0-9a-zA-Z-._~!$&'\\(\\)*+,;=:@\\/?]|%[0-9A-Fa-f]{2})*)$");
		Matcher matcher = pattern.matcher(this.raw);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return this.raw;
	}

}
