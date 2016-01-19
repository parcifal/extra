package eu.parcifal.extra.net.http;

import eu.parcifal.extra.net.Responder;
import eu.parcifal.extra.print.Console;

public class HttpResponder extends Responder {

	@Override
	protected byte[] response(String request) {
		Console.log(request);

		return "200 OK".getBytes();
	}

}
