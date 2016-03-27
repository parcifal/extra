package eu.parcifal.plus.net;

import java.io.IOException;
import java.net.Socket;

import eu.parcifal.plus.logic.Poolable;
import eu.parcifal.plus.print.Console;

/**
 * Provides a response to a request made via a socket.
 * 
 * @author Michaël van de Weerd
 */
public abstract class Exchanger extends Poolable implements Runnable {

	private static final String MESSAGE_HANDLING = "handling request from client at %1$s:%2$d on port %3$d";

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
			
			if(this.client == null || this.client.isClosed()) {
				synchronized (this.monitor()) {
					this.notifyObservers();

					try {
						this.monitor().wait();
					} catch (InterruptedException exception) {
						Console.warn(exception);
					}
				}
			}

			try {
				Console.log(MESSAGE_HANDLING, client.getInetAddress(), client.getPort(), client.getLocalPort());

				byte[] request = new byte[REQUEST_BUFFER_SIZE];

				client.getInputStream().read(request);

				byte[] response = this.response(request);

				client.getOutputStream().write(response);
			} catch (IOException exception) {
				Console.warn(exception);
			} finally {
				try {
					this.client.close();
				} catch (IOException exception) {
					Console.warn(exception);
				}
			}
		}
	}

	@Override
	public void initialise(Object... args) {
		if (args.length < 1 || !(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			this.client = (Socket) args[0];
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
