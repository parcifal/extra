package eu.parcifal.extra.net.http;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.logic.Router;
import eu.parcifal.extra.net.PortListener;
import eu.parcifal.extra.net.Exchanger;

public class HTTPListener extends PortListener {

	private static final int DEFAULT_PORT = 80;

	private HTTPExchangerPool pool;

	public HTTPListener(int port, Router router) {
		super(port);

		this.pool = new HTTPExchangerPool(router);
	}

	public HTTPListener(Router router) {
		this(DEFAULT_PORT, router);
	}

	@Override
	protected Pool<Exchanger> pool() {
		return this.pool;
	}

}
