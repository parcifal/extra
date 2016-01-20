package eu.parcifal.extra.net.http;

import java.io.UnsupportedEncodingException;

import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.Responder;
import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;
import eu.parcifal.extra.throwing.RouteNotFoundException;

public class HTTPExchanger extends Responder {

	private Router router;

	public HTTPExchanger(Router router) {
		this.router = router;
	}

	@Override
	protected byte[] response(byte[] request) {
		try {
			HTTPRequest httpRequest = HTTPRequest.parse(new String(request, "UTF-8"));

			HTTPResponse httpResponse = (HTTPResponse) router.route(httpRequest.header("Host").value(), httpRequest);

			return httpResponse.toBytes();
		} catch (UnsupportedEncodingException | IllegalArgumentException e) {
			return new HTTPResponse(HTTPResponse.STATUS_400).toBytes();
		} catch (RouteNotFoundException rne) {
			return new HTTPResponse(HTTPResponse.STATUS_404).toBytes();
		} catch (Exception e) {
			Console.warn(Level.HIGH, e.getMessage());

			return new HTTPResponse(HTTPResponse.STATUS_500).toBytes();
		}
	}

}
