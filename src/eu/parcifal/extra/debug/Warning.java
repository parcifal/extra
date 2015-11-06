package eu.parcifal.extra.debug;

import java.time.LocalDateTime;

public class Warning extends Output {
	/**
	 * The format in which a warning prints its content.
	 */
	public final static String STRING_FORMAT = "[%1$s] %2$s %4$s\tWARNING %3$s";

	private Level level;

	/**
	 * Construct a new log with a source class and a object as content. The
	 * content is represented as the result of its toString() method.
	 * 
	 * @param source
	 *            The source of the new log.
	 * @param content
	 *            The content object of the new log.
	 */
	public Warning(Class<?> source, Object content, Level level) {
		super(source, content);

		this.level = level;
	}

	private Warning(LocalDateTime time, Class<?> source, Object content, Level level) {
		super(time, source, content);

		this.level = level;
	}

	/**
	 * Return the level of the current warning.
	 * 
	 * @return The level of the current warning.
	 */
	public Level getLevel() {
		return this.level;
	}

	@Override
	public void print() {
		System.out.println(String.format(STRING_FORMAT, this.time, this.source.getName(), this.content, this.level));
	}

	@Override
	public Output clone() {
		return new Warning(this.time, this.source, this.content, this.level);
	}

	/**
	 * Contains the different levels a warning can have.
	 * 
	 * @author Michaël van de Weerd
	 */
	public enum Level {
		LOW, HIGH, SEV
	}

}
