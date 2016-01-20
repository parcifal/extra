package eu.parcifal.extra.net.http;

public class HTTPResponse extends HTTPExchange {

	private final static String FORMAT_STATUS_LINE = "%1s %2s %3s";

	private final static HTTPVersion DEFAULT_VERSION = new HTTPVersion(1, 1);

	public final static HTTPStatus STATUS_100 = new HTTPStatus(100, "Continue");
	public final static HTTPStatus STATUS_101 = new HTTPStatus(100, "Switching Protocols");
	public final static HTTPStatus STATUS_200 = new HTTPStatus(200, "OK");
	public final static HTTPStatus STATUS_201 = new HTTPStatus(201, "Created");
	public final static HTTPStatus STATUS_202 = new HTTPStatus(202, "Accepted");
	public final static HTTPStatus STATUS_203 = new HTTPStatus(203, "Non-Authoritative Information");
	public final static HTTPStatus STATUS_204 = new HTTPStatus(204, "No Content");
	public final static HTTPStatus STATUS_205 = new HTTPStatus(205, "Reset Content");
	public final static HTTPStatus STATUS_206 = new HTTPStatus(206, "Partial Content");

	public final static HTTPStatus STATUS_400 = new HTTPStatus(400, "Bad Request");
	public final static HTTPStatus STATUS_404 = new HTTPStatus(404, "Not Found");

	public final static HTTPStatus STATUS_500 = new HTTPStatus(500, "Internal Server Error");

	private HTTPVersion version;

	private HTTPStatus status;

	public HTTPResponse(HTTPVersion version, HTTPStatus status) {
		this.version = version;
		this.status = status;
	}

	public HTTPResponse(HTTPStatus status) {
		this(DEFAULT_VERSION, status);
	}

	public byte[] toBytes() {
		return this.toString().getBytes();
	}

	@Override
	public String toString() {
		return String.format(FORMAT_STATUS_LINE, this.version, this.status.code(), this.status.reasonPhrase());
	}

}
