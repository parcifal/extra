package eu.parcifal.extra.net.http;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.net.PortListener;
import eu.parcifal.extra.net.Responder;

public class HTTPListener extends PortListener {

	private static final HTTPResponderPool POOL = new HTTPResponderPool();

	private static final int DEFAULT_PORT = 80;

	public HTTPListener(int port) {
		super(port);
	}

	public HTTPListener() {
		super(DEFAULT_PORT);
	}

	@Override
	protected Pool<Responder> pool() {
		return POOL;
	}

}
