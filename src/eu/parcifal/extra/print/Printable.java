package eu.parcifal.extra.print;

import java.time.LocalDateTime;

public class Printable {

	private static String format = "[%1$s] %2$s %3$s \"%4$s\"";

	protected LocalDateTime time = LocalDateTime.now();

	protected Class<?> source;

	protected Object content;

	protected boolean debug;

	public Printable(Class<?> source, Object content, boolean debug) {
		this.source = source;
		this.content = content;
		this.debug = false;
	}

	public Printable(Class<?> source, String content, Object... args) {
		this(source, String.format(content, args), false);
	}

	protected Printable(LocalDateTime time, Class<?> source, Object content, boolean debug) {
		this.time = time;
		this.source = source;
		this.content = content;
		this.debug = debug;
	}

	public LocalDateTime getTime() {
		return this.time;
	}

	public Class<?> getSource() {
		return this.source;
	}

	public Object getContent() {
		return this.content;
	}

	public void print(boolean debug) {
		if (!this.debug || debug) {
			System.out.println(String.format(Printable.format, this.time, this.source.getName(), this.content));
		}
	}

	public Printable clone() {
		return new Printable(this.time, this.source, this.content, this.debug);
	}

	static public void setFormat(String format) {
		Printable.format = format;
	}

}
