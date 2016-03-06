package eu.parcifal.plus.net;

import java.io.IOException;
import java.net.Socket;

import eu.parcifal.plus.logic.Poolable;
import eu.parcifal.plus.print.Console;
import eu.parcifal.plus.print.output.Warning.Level;

/**
 * Provides a response to a request made via a socket.
 * 
 * @author Michaël van de Weerd
 */
public abstract class Exchanger extends Poolable implements Runnable {

	private static final String MESSAGE_HANDLING = "handling request from client at %1$s";

	private static final int REQUEST_BUFFER_SIZE = 8192;

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
				Console.log(MESSAGE_HANDLING, client.getInetAddress());

				byte[] request = new byte[REQUEST_BUFFER_SIZE];

				client.getInputStream().read(request);

				byte[] response = this.response(request);

				client.getOutputStream().write(response);
			} catch (IOException ioe) {
				Console.warn(Level.HIGH, ioe.getMessage());
			} finally {
				try {
					this.client.close();
					this.client = null;
				} catch (IOException ioe) {
					Console.warn(Level.HIGH, ioe.getMessage());
				}
			}

			this.notifyObservers();

			synchronized (this.monitor()) {
				while (this.client == null || this.client.isClosed()) {
					try {
						this.monitor().wait();
					} catch (InterruptedException e) {
						Console.warn(Level.HIGH, e.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void initialise(Object... args) {
		if (args.length < 1 || !(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			synchronized (this.monitor()) {
				this.client = (Socket) args[0];
				this.monitor().notify();
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
