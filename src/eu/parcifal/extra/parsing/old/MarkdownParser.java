package eu.parcifal.extra.parsing.old;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownParser implements Parser {

	public final static int DISABLE_HEADING_1 = 1;
	public final static int DISABLE_HEADING_2 = 1 << 1;
	public final static int DISABLE_HEADING_3 = 1 << 2;
	public final static int DISABLE_HEADING_4 = 1 << 3;
	public final static int DISABLE_HEADING_5 = 1 << 4;
	public final static int DISABLE_HEADING_6 = 1 << 5;
	public final static int DISABLE_BLOCKQUOTE = 1 << 6;
	public final static int DISABLE_LIST = 1 << 7;
	public final static int DISABLE_CODEBLOCK = 1 << 8;
	public final static int DISABLE_HORIZONTAL_RULE = 1 << 9;
	public final static int DISABLE_LINK = 1 << 10;
	public final static int DISABLE_EMPHASIS = 1 << 11;
	public final static int DISABLE_IMAGES = 1 << 12;

	private final static String HEADING_1_FORMAT = "<h1>%1$s</h1>";
	private final static String HEADING_2_FORMAT = "<h2>%1$s</h2>";
	private final static String HEADING_3_FORMAT = "<h3>%1$s</h3>";
	private final static String HEADING_4_FORMAT = "<h4>%1$s</h4>";
	private final static String HEADING_5_FORMAT = "<h5>%1$s</h5>";
	private final static String HEADING_6_FORMAT = "<h6>%1$s</h6>";
	private final static String BLOCKQUOTE_FORMAT = "<blockquote>%1$s</blockquote>";
	private final static String CODEBLOCK_FORMAT = "<pre><code>%1$s</code></pre>";
	private final static String HORIZONTAL_RULE_FORMAT = "<hr>";
	private final static String LINK_FORMAT = "<a href=\"%2$s\">%1$s</a>";
	private final static String EMPHASIS_FORMAT = "<em>%1$s</em>";
	private final static String STRONG_FORMAT = "<strong>%1$s</strong>";
	private final static String CODE_FORMAT = "<code>%1$s</code>";

	private final static String HEADING_1_PATTERN = "^#{1} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";
	private final static String HEADING_2_PATTERN = "^#{2} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";
	private final static String HEADING_3_PATTERN = "^#{3} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";
	private final static String HEADING_4_PATTERN = "^#{4} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";
	private final static String HEADING_5_PATTERN = "^#{5} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";
	private final static String HEADING_6_PATTERN = "^#{6} ((?:[^#\r\n]|(?:(?<=\\\\#))+))(?:(?<!\\\\)#*)$";

	private final static String LINK_PATTERN = "(?<!\\\\)\\[((?:[^\\]]|(?:(?<=\\\\)\\]))+)\\]\\(((?:[^\\)]|(?:(?<=\\\\)\\)))+)\\)";
	private final static String EMPHASIS_PATTERN = "(?<!\\\\)\\*((?:[^\\*]|(?:(?<=\\\\)\\*))+)\\*";
	private final static String STRONG_PATTERN = "(?<!\\\\)\\*\\*((?:[^\\*]|(?:(?<=\\\\)\\*))+)\\*\\*";
	private final static String CODE_PATTERN = "(?<!\\\\)\\`((?:[^\\`]|(?:(?<=\\\\)\\`))+)\\`";

	public String parse(String plain, int flags) {
		plain = this.replace(plain, HEADING_1_PATTERN, HEADING_1_FORMAT);
		plain = this.replace(plain, HEADING_2_PATTERN, HEADING_2_FORMAT);
		plain = this.replace(plain, HEADING_3_PATTERN, HEADING_3_FORMAT);
		plain = this.replace(plain, HEADING_4_PATTERN, HEADING_4_FORMAT);
		plain = this.replace(plain, HEADING_5_PATTERN, HEADING_5_FORMAT);
		plain = this.replace(plain, HEADING_6_PATTERN, HEADING_6_FORMAT);
		plain = this.replace(plain, LINK_PATTERN, LINK_FORMAT);
		plain = this.replace(plain, STRONG_PATTERN, STRONG_FORMAT);
		plain = this.replace(plain, EMPHASIS_PATTERN, EMPHASIS_FORMAT);
		plain = this.replace(plain, CODE_PATTERN, CODE_FORMAT);

		return plain;
	}

	private String replace(String plain, Pattern pattern, String format) {
		Matcher matcher = pattern.matcher(plain);

		StringBuffer buffer = new StringBuffer();

		while (matcher.find()) {
			Object[] groups = new Object[matcher.groupCount()];

			for (int i = 0; i < groups.length; i++) {
				groups[i] = matcher.group(i + 1);
			}

			matcher.appendReplacement(buffer, String.format(format, groups));
		}

		return matcher.appendTail(buffer).toString();

	}

	private String replace(String plain, String pattern, String format) {
		return this.replace(plain, Pattern.compile(pattern), format);
	}

}
