package eu.parcifal.extra.debug;

import java.time.LocalDateTime;

/**
 * Formats a time, source and content in a readable way and can be printed to
 * the console.
 * 
 * @author Michaël van de Weerd
 */
public class Printable {
	/**
	 * The format of the printed value of a printable.
	 */
	private static String format = "[%1$s] %2$s %3$s \"%4$s\"";

	/**
	 * The time at which the current output was created.
	 */
	protected LocalDateTime time = LocalDateTime.now();

	/**
	 * The source of the current printable.
	 */
	protected Class<?> source;

	/**
	 * The content of the current printable.
	 */
	protected Object content;

	/**
	 * Construct a new printable.
	 * 
	 * @param source
	 *            The source class of the new printable.
	 * @param content
	 *            The content of the new printable.
	 */
	public Printable(Class<?> source, Object content) {
		this.source = source;
		this.content = content;
	}

	/**
	 * Construct a new printable with a formated string as content, using the
	 * static format method in the string class.
	 * 
	 * @param source
	 *            The source class of the new printable.
	 * @param content
	 *            The formated string content of the new printable.
	 * @param args
	 *            The arguments to be used in the formatted string.
	 */
	public Printable(Class<?> source, String content, Object... args) {
		this(source, String.format(content, args));
	}

	/**
	 * Construct a new printable with a time. Only meant to be used when a
	 * printable is cloned.
	 * 
	 * @param time
	 *            The time at which the new printable was created.
	 * @param source
	 *            The source class of the new printable.
	 * @param content
	 *            The content of the current printable.
	 */
	protected Printable(LocalDateTime time, Class<?> source, Object content) {
		this.time = time;
		this.source = source;
		this.content = content;
	}

	/**
	 * Return the time at which the current printable was created.
	 * 
	 * @return The time at which the current printable was created.
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * Return the source class of the current printable.
	 * 
	 * @return The source class of the current printable.
	 */
	public Class<?> getSource() {
		return this.source;
	}

	/**
	 * Return the content of the current printable.
	 * 
	 * @return The content of the current printable.
	 */
	public Object getContent() {
		return this.content;
	}

	/**
	 * Print the current printable as a string.
	 */
	public void print() {
		System.out.println(String.format(Printable.format, this.time, this.source.getName(), this.content));
	}

	/**
	 * Return an exact duplicate of the current printable.
	 * 
	 * @return An exact duplicate of the current printable.
	 */
	public Printable clone() {
		return new Printable(this.time, this.source, this.content);
	}

	/**
	 * Set the format of the printed value of an printable.
	 * 
	 * @param format
	 *            The new format for the printed value of an printable.
	 */
	static public void setFormat(String format) {
		Printable.format = format;
	}

}
