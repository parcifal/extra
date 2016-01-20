package eu.parcifal.extra.net.http;

public class HTTPHeader {

	private final static String STRING_FORMAT = "%1$s: %2$s";

	private String name;
	private String value;

	public HTTPHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String name() {
		return this.name;
	}

	public String value() {
		return this.value;
	}

	public void name(String name) {
		this.name = name;
	}

	public void value(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format(STRING_FORMAT, this.name, this.value);
	}

}
