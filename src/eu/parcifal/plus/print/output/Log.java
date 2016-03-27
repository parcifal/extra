package eu.parcifal.plus.print.output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log extends Printable {

	private static final long serialVersionUID = -8779120534402398603L;

	private final static String PRINT_FORMAT = "LOG     [%1$s] %2$s%3$s";
	private final static String LINE_FORMAT = "\r\n : %1$s";

	private static final String TIME_FORMAT = "HH:mm:ss.SSS";

	public Log(Object content) {
		super(content);
	}

	protected Log(LocalDateTime time, String source, Object content) {
		super(time, source, content);
	}

	@Override
	public void print(boolean debug) {
		String time = this.time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
		String source = this.source.toString();
		String content = "";

		Pattern pattern = Pattern.compile("^(.*)$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(this.content.toString());

		while (matcher.find()) {
			content += String.format(LINE_FORMAT, matcher.group(1));
		}

		System.out.println(String.format(PRINT_FORMAT, time, source, content));
	}

	@Override
	public Printable clone() {
		return new Log(this.time, this.source, this.content);
	}

}
