package eu.parcifal.extra.net.http;

import java.net.Socket;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.Responder;

public class HTTPResponderPool extends Pool<Responder> {

	private static Router ROUTER;

	public HTTPResponderPool(Router router) {
		ROUTER = router;
	}

	@Override
	protected Responder instantiate(Object... args) {
		if (!(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			Responder responder = new HTTPExchanger(ROUTER);

			responder.observe(this);
			responder.initialise((Socket) args[0]);

			new Thread(responder).start();

			return responder;
		}
	}

}
