package eu.parcifal.extra.net.http;

public class HTTPResponse extends HTTPExchange {

	private final static String FORMAT_STRING_HEADER = "%1$s %2$s\n%3$s";
	private final static String FORMAT_STRING_BODY = "%1$s\n%2$s";

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

	public final static HTTPStatus STATUS_300 = new HTTPStatus(300, "Multiple Choices");
	public final static HTTPStatus STATUS_301 = new HTTPStatus(301, "Moved Permanently");
	public final static HTTPStatus STATUS_302 = new HTTPStatus(302, "Found");
	public final static HTTPStatus STATUS_303 = new HTTPStatus(303, "See Other");
	public final static HTTPStatus STATUS_304 = new HTTPStatus(304, "Not Modified");
	public final static HTTPStatus STATUS_305 = new HTTPStatus(305, "Use Proxy");
	public final static HTTPStatus STATUS_307 = new HTTPStatus(307, "Temporary Redirect");

	public final static HTTPStatus STATUS_400 = new HTTPStatus(400, "Bad Request");
	public final static HTTPStatus STATUS_401 = new HTTPStatus(401, "Unauthorized");
	public final static HTTPStatus STATUS_402 = new HTTPStatus(402, "Payment Required");
	public final static HTTPStatus STATUS_403 = new HTTPStatus(403, "Forbidden");
	public final static HTTPStatus STATUS_404 = new HTTPStatus(404, "Not Found");
	public final static HTTPStatus STATUS_405 = new HTTPStatus(405, "Method Not Allowed");
	public final static HTTPStatus STATUS_406 = new HTTPStatus(406, "Not Acceptable");
	public final static HTTPStatus STATUS_407 = new HTTPStatus(407, "Proxy Authentication Required");
	public final static HTTPStatus STATUS_408 = new HTTPStatus(408, "Request Timeout");
	public final static HTTPStatus STATUS_409 = new HTTPStatus(409, "Conflict");
	public final static HTTPStatus STATUS_410 = new HTTPStatus(410, "Gone");
	public final static HTTPStatus STATUS_411 = new HTTPStatus(411, "Length Required");
	public final static HTTPStatus STATUS_412 = new HTTPStatus(412, "Precondition Failed");
	public final static HTTPStatus STATUS_413 = new HTTPStatus(413, "Request Entity Too Large");
	public final static HTTPStatus STATUS_414 = new HTTPStatus(414, "Request-URI Too Long");
	public final static HTTPStatus STATUS_415 = new HTTPStatus(415, "Unsupported Media Type");
	public final static HTTPStatus STATUS_416 = new HTTPStatus(416, "Requested Range Not Satisfiable");
	public final static HTTPStatus STATUS_417 = new HTTPStatus(417, "Expectation Failed");

	public final static HTTPStatus STATUS_500 = new HTTPStatus(500, "Internal Server Error");
	public final static HTTPStatus STATUS_501 = new HTTPStatus(501, "Not Implemented");
	public final static HTTPStatus STATUS_502 = new HTTPStatus(502, "Bad Gateway");
	public final static HTTPStatus STATUS_503 = new HTTPStatus(503, "Service Unavailable");
	public final static HTTPStatus STATUS_504 = new HTTPStatus(504, "Gateway Timeout");
	public final static HTTPStatus STATUS_505 = new HTTPStatus(505, "HTTP Version Not Supported");

	private HTTPVersion version;

	private HTTPStatus status;

	private String body = "";

	public HTTPResponse(HTTPVersion version, HTTPStatus status) {
		this.version = version;
		this.status = status;
	}

	public HTTPResponse(HTTPStatus status) {
		this(DEFAULT_VERSION, status);
	}

	public void body(String body) {
		this.body = body;
		this.header("Content-Length", String.valueOf(body.length()));
	}

	@Override
	public String headers() {
		return String.format(FORMAT_STRING_HEADER, this.version, this.status, super.headers());
	}

	public String body() {
		return String.format(FORMAT_STRING_BODY, this.headers(), this.body);
	}

	@Override
	public String toString() {
		return this.body();
	}

}
