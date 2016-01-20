package eu.parcifal.extra.net.http;

public class HTTPStatus {

	private final static String STRING_FORMAT = "%1$s %2$s";

	private int code;
	private String reasonPhrase;

	public HTTPStatus(int code, String reasonPhrase) {
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

	public int code() {
		return this.code;
	}

	public String reasonPhrase() {
		return this.reasonPhrase;
	}

	@Override
	public String toString() {
		return String.format(STRING_FORMAT, this.code, this.reasonPhrase);
	}

}
