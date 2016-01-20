package eu.parcifal.extra.print.output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Debug extends Printable {

	private static final long serialVersionUID = -4581140992057274821L;

	private final static String STRING_FORMAT = "[%1$s]\t\tDEBUG\t%2$s\t%3$s";

	private static final String TIME_FORMAT = "HH:mm:ss.SSS";

	public Debug(Object content) {
		super(content);
	}

	private Debug(LocalDateTime time, String source, Object content) {
		super(time, source, content);
	}

	@Override
	public void print(boolean debug) {
		if (debug) {
			String time = this.time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
			String source = this.source.toString();
			String content = this.content.toString();

			System.out.println(String.format(STRING_FORMAT, time, source, content));
		}
	}

	@Override
	public Debug clone() {
		return new Debug(this.time, this.source, this.content);
	}

}
