package eu.parcifal.extra.print;

import java.time.LocalDateTime;

public class Warning extends Printable {

	public final static String STRING_FORMAT = "[%1$s] %2$s %4$s\tWARNING %3$s";

	private Level level;

	public Warning(Class<?> source, Object content, Level level, boolean debug) {
		super(source, content, debug);

		this.level = level;
	}

	private Warning(LocalDateTime time, Class<?> source, Object content, Level level, boolean debug) {
		super(time, source, content, debug);

		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}

	@Override
	public void print(boolean debug) {
		if (!this.debug || debug) {
			System.out
					.println(String.format(STRING_FORMAT, this.time, this.source.getName(), this.content, this.level));
		}
	}

	@Override
	public Printable clone() {
		return new Warning(this.time, this.source, this.content, this.level, this.debug);
	}

	public enum Level {
		LOW, HIGH, FATAL
	}

}
