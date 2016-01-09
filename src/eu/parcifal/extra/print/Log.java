package eu.parcifal.extra.print;

import java.time.LocalDateTime;

public class Log extends Printable {

	public final static String STRING_FORMAT = "[%1$s] %2$s\tLOG\t%3$s";

	public Log(Class<?> source, Object content, boolean debug) {
		super(source, content, debug);
	}

	private Log(LocalDateTime time, Class<?> source, Object content, boolean debug) {
		super(time, source, content, debug);
	}

	@Override
	public void print(boolean debug) {
		if (!this.debug || debug) {
			System.out.println(String.format(STRING_FORMAT, this.time, this.source.getName(), this.content));
		}
	}

	@Override
	public Printable clone() {
		return new Log(this.time, this.source, this.content, this.debug);
	}

}
