package eu.parcifal.plus.print.output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Warning extends Printable {

	private static final long serialVersionUID = 7850251421780052796L;

	private final static String STRING_FORMAT = "WARNING [%1$s] %2$s: %3$s";
	private final static String LINE_FORMAT = "\r\n @ %1$s";

	private static final String TIME_FORMAT = "HH:mm:ss.SSS";

	public Warning(Exception content) {
		super(content);
	}

	private Warning(LocalDateTime time, String source, Object content) {
		super(time, source, content);
	}

	@Override
	public void print(boolean debug) {
		Exception exception = (Exception) content;
		
		String time = this.time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
		String type = exception.toString();
		String stacktrace = "";
		
		for(StackTraceElement element : exception.getStackTrace()) {
			stacktrace += String.format(LINE_FORMAT, element.toString());
		}

		System.out.println(String.format(STRING_FORMAT, time, type, stacktrace));
	}

	@Override
	public Printable clone() {
		return new Warning(this.time, this.source, this.content);
	}

}
