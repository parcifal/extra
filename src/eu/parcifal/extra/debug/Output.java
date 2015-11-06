package eu.parcifal.extra.debug;

import java.time.LocalDateTime;

public class Output {
	/**
	 * The format of the printed value of an output.
	 */
	private static String format = "[%1$s] %2$s %3$s \"%4$s\"";

	/**
	 * The time at which the current output was created.
	 */
	protected LocalDateTime time = LocalDateTime.now();

	/**
	 * The source of the current output.
	 */
	protected Class<?> source;

	/**
	 * The content of the current output.
	 */
	protected Object content;

	/**
	 * Construct a new output.
	 * 
	 * @param source
	 *            The source class of the new output.
	 * @param content
	 *            The content of the new output.
	 */
	public Output(Class<?> source, Object content) {
		this.source = source;
		this.content = content;
	}

	/**
	 * Construct a new output with a formated string as content, using the
	 * static format method in the string class.
	 * 
	 * @param source
	 *            The source class of the new output.
	 * @param content
	 *            The formated string content of the new output.
	 * @param args
	 *            The arguments to be used in the formatted string.
	 */
	public Output(Class<?> source, String content, Object... args) {
		this(source, String.format(content, args));
	}

	/**
	 * Construct a new output with a time. Only meant to be used when a output
	 * is cloned.
	 * 
	 * @param time
	 *            The time at which the new output was created.
	 * @param source
	 *            The source class of the new output.
	 * @param content
	 *            The content of the current output.
	 */
	protected Output(LocalDateTime time, Class<?> source, Object content) {
		this.time = time;
		this.source = source;
		this.content = content;
	}

	/**
	 * Return the time at which the current output was created.
	 * 
	 * @return The time at which the current output was created.
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * Return the source class of the current output.
	 * 
	 * @return The source class of the current output.
	 */
	public Class<?> getSource() {
		return this.source;
	}

	/**
	 * Return the content of the current log.
	 * 
	 * @return The content of the current log.
	 */
	public Object getContent() {
		return this.content;
	}

	/**
	 * Print the current output as a string.
	 */
	public void print() {
		System.out.println(String.format(Output.format, this.time, this.source.getName(), this.content));
	}

	/**
	 * Return an exact duplicate of the current output.
	 * 
	 * @return An exact duplicate of the current output.
	 */
	public Output clone() {
		return new Output(this.time, this.source, this.content);
	}

	/**
	 * Set the format of the printed value of an output.
	 * 
	 * @param format
	 *            The new format for the printed value of an output.
	 */
	static public void setFormat(String format) {
		Output.format = format;
	}

}
