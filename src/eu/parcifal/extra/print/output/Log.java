package eu.parcifal.extra.print.output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log extends Printable {

	private static final long serialVersionUID = -8779120534402398603L;

	private final static String FORMAT = "[%1s]\t\tLOG\t%2s\t%3s";

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
		String content = this.content.toString();

		System.out.println(String.format(FORMAT, time, source, content));
	}

	@Override
	public Printable clone() {
		return new Log(this.time, this.source, this.content);
	}

}
