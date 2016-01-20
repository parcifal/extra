package eu.parcifal.extra.print.output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Warning extends Printable {

	private static final long serialVersionUID = 7850251421780052796L;

	private final static String STRING_FORMAT = "[%1$s]\t%2$s\tWARNING\t%3$s\t%4$s";

	private static final String TIME_FORMAT = "HH:mm:ss.SSS";

	private Level level;

	public Warning(Object content, Level level) {
		super(content);

		this.level = level;
	}

	private Warning(LocalDateTime time, String source, Object content, Level level) {
		super(time, source, content);

		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}

	@Override
	public void print(boolean debug) {
		String time = this.time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
		String level = this.level.name();
		String source = this.source.toString();
		String content = this.content.toString();

		System.out.println(String.format(STRING_FORMAT, time, level, source, content));
	}

	@Override
	public Printable clone() {
		return new Warning(this.time, this.source, this.content, this.level);
	}

	public enum Level {
		LOW, HIGH, FATAL
	}

}
