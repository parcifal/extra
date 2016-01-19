package eu.parcifal.extra.net;

import java.io.IOException;
import java.net.Socket;

import eu.parcifal.extra.logic.Poolable;
import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;

/**
 * Provides a response to a request made via a socket.
 * 
 * @author Michaël van de Weerd
 */
public abstract class Responder extends Poolable implements Runnable {

	private Socket client;

	private int runCycle;

	private boolean running = false;

	public final boolean running() {
		return this.running;
	}

	public final int runCycle() {
		return this.runCycle;
	}

	public final void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		this.running = true;

		while (this.running) {
			this.runCycle++;

			try {
				byte[] request = new byte[8192];

				client.getInputStream().read(request);

				byte[] response = this.response(request);

				client.getOutputStream().write(response);
			} catch (IOException ioe) {
				Console.warn(Level.HIGH, ioe.getMessage());
			}

			try {
				this.client.close();
			} catch (IOException ioe) {
				Console.warn(Level.HIGH, ioe.getMessage());
			}

			this.notifyObservers();

			synchronized (this.manager()) {
				while (this.client.isClosed()) {
					try {
						this.manager().wait();
					} catch (InterruptedException e) {
						Console.warn(Level.HIGH, e.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void initialise(Object... args) {
		if (!(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			synchronized (this.manager()) {
				this.client = (Socket) args[0];
				this.manager().notify();
			}
		}
	}

	/**
	 * Return the response of the current responder to the specified request.
	 * 
	 * @param request
	 *            The request to which the current responder needs to respond.
	 * @return The response of the current responder.
	 */
	protected abstract byte[] response(byte[] request);

}
