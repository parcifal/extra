package eu.parcifal.extra.net.http;

import java.net.Socket;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.net.Responder;

public class HttpResponderPool extends Pool<Responder> {

	@Override
	protected Responder instantiate(Object... args) {
		if (!(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			Responder responder = new HttpResponder();

			responder.observe(this);
			responder.initialise((Socket) args[0]);

			new Thread(responder).start();

			return responder;
		}
	}

}
