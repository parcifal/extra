package eu.parcifal.extra.net.http;

import java.net.Socket;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.Exchanger;

public class HTTPExchangerPool extends Pool<Exchanger> {

	private static Router ROUTER;

	public HTTPExchangerPool(Router router) {
		ROUTER = router;
	}

	@Override
	protected Exchanger instantiate(Object... args) {
		if (!(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			Exchanger responder = new HTTPExchanger(ROUTER);

			responder.observe(this);
			responder.initialise((Socket) args[0]);

			new Thread(responder).start();

			return responder;
		}
	}

}
