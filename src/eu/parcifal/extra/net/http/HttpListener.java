package eu.parcifal.extra.net.http;

import eu.parcifal.extra.logic.Pool;
import eu.parcifal.extra.net.PortListener;
import eu.parcifal.extra.net.Responder;

public class HttpListener extends PortListener {

	private static final HttpResponderPool POOL = new HttpResponderPool();

	private static final int DEFAULT_PORT = 80;

	public HttpListener(int port) {
		super(port);
	}

	public HttpListener() {
		super(DEFAULT_PORT);
	}

	@Override
	protected Pool<Responder> pool() {
		return POOL;
	}

}
