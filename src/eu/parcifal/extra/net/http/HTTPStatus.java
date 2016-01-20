package eu.parcifal.extra.net.http;

public class HTTPStatus {

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

}
