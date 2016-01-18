package eu.parcifal.extra.parsing;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parser {

	private static final String MESSAGE_NOT_IMPLEMENTED_REPLACEMENT = "replace method can not be called without an implementation for the replacement method";

	private Pattern pattern;

	public Parser(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	public String replace(String plain) {
		Matcher matcher = pattern.matcher(plain);

		StringBuffer buffer = new StringBuffer();

		while (matcher.find()) {
			ArrayList<String> groupList = new ArrayList<String>();

			for (int i = 0; i < matcher.groupCount() + 1; i++) {
				groupList.add(matcher.group(i));
			}

			String[] groups = new String[groupList.size()];

			matcher.appendReplacement(buffer, replacement(groupList.toArray(groups)));
		}

		matcher.appendTail(buffer);

		return buffer.toString();
	}

	public String replacement(String... groups) {
		throw new RuntimeException(MESSAGE_NOT_IMPLEMENTED_REPLACEMENT);
	}

}
