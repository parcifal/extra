package eu.parcifal.extra.net.http;

import java.io.UnsupportedEncodingException;

import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.Exchanger;
import eu.parcifal.extra.throwing.RouteNotFoundException;

public class HTTPExchanger extends Exchanger {

	private Router router;

	public HTTPExchanger(Router router) {
		this.router = router;
	}

	@Override
	protected byte[] response(byte[] request) {
		try {
			HTTPRequest httpRequest = HTTPRequest.parse(new String(request, "UTF-8"));

			HTTPResponse httpResponse;

			switch (httpRequest.method()) {
			case GET:
				httpResponse = (HTTPResponse) router.route(httpRequest.header("Host").value(), httpRequest);
				
				return httpResponse.toBytes();
			case HEAD:
				httpResponse = (HTTPResponse) router.route(httpRequest.header("Host").value(), httpRequest);

				return httpResponse.toBytes();
			default:
				return new HTTPResponse(HTTPResponse.STATUS_405).toBytes();
			}
		} catch (UnsupportedEncodingException | IllegalArgumentException e) {
			return new HTTPResponse(HTTPResponse.STATUS_400).toBytes();
		} catch (RouteNotFoundException rne) {
			return new HTTPResponse(HTTPResponse.STATUS_404).toBytes();
		} catch (Exception e) {
			e.printStackTrace();

			return new HTTPResponse(HTTPResponse.STATUS_500).toBytes();
		}
	}

}
