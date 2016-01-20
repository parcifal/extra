package eu.parcifal.extra.net.http;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.PortListener;
import eu.parcifal.extra.net.Responder;

public class HTTPListener extends PortListener {

	private static final int DEFAULT_PORT = 80;

	private HTTPResponderPool pool;

	public HTTPListener(int port, Router router) {
		super(port);

		this.pool = new HTTPResponderPool(router);
	}

	public HTTPListener(Router router) {
		this(DEFAULT_PORT, router);
	}

	@Override
	protected Pool<Responder> pool() {
		return this.pool;
	}

}
