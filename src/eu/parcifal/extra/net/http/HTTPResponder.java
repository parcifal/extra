package eu.parcifal.extra.net.http;

import java.io.UnsupportedEncodingException;

import eu.parcifal.extra.net.Responder;
import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;

public class HTTPResponder extends Responder {

	@Override
	protected byte[] response(byte[] request) {
		try {
			try {
				HTTPRequest httpRequest = HTTPRequest.parse(new String(request, "UTF-8"));

				Console.log(httpRequest);
			} catch (IllegalArgumentException iae) {
				return "HTTP/1.1 400 Bad Request".getBytes();
			}

		} catch (UnsupportedEncodingException uee) {
			Console.warn(Level.HIGH, uee.getMessage());
		}

		return "HTTP/1.1 500 Internal Server Error".getBytes();
	}

}
