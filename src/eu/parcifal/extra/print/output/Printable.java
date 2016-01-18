package eu.parcifal.extra.print.output;

import java.time.LocalDateTime;

public abstract class Printable extends Throwable {

	private static final long serialVersionUID = 2501252183592632385L;

	protected LocalDateTime time = LocalDateTime.now();

	protected Object content;

	protected String source;

	public Printable(Object content) {
		this.content = content;

		// the console will be the first element
		this.source = super.getStackTrace()[1].toString();
	}

	protected Printable(LocalDateTime time, String source, Object content) {
		this.time = time;
		this.source = source;
		this.content = content;
	}

	public LocalDateTime getTime() {
		return this.time;
	}

	public Object getContent() {
		return this.content;
	}

	public String getSource() {
		return this.source;
	}

	abstract public void print(boolean debug);

	@Override
	abstract public Printable clone();

}
