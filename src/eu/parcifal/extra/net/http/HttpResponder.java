package eu.parcifal.extra.net.http;

import eu.parcifal.extra.net.Responder;

public class HttpResponder extends Responder {

	@Override
	protected byte[] response(byte[] request) {
		return "HTTP/1.0 500 Internal Server Error".getBytes();
	}

}
