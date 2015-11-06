package eu.parcifal.extra.debug;

import java.time.LocalDateTime;

public class Log extends Output {
	/**
	 * The format in which a log prints its content.
	 */
	public final static String STRING_FORMAT = "[%1$s] %2$s\tLOG\t%3$s";

	/**
	 * Construct a new log with a source class and a object as content. The
	 * content is represented as the result of its toString() method.
	 * 
	 * @param source
	 *            The source of the new log.
	 * @param content
	 *            The content object of the new log.
	 */
	public Log(Class<?> source, Object content) {
		super(source, content);
	}

	private Log(LocalDateTime time, Class<?> source, Object content) {
		super(time, source, content);
	}

	@Override
	public void print() {
		System.out.println(String.format(STRING_FORMAT, this.time, this.source.getName(), this.content));
	}

	@Override
	public Output clone() {
		return new Log(this.time, this.source, this.content);
	}

}
