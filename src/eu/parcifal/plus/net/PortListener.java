package eu.parcifal.plus.net;

import java.io.IOException;
import java.net.ServerSocket;

import eu.parcifal.plus.logic.Pool;
import eu.parcifal.plus.print.Console;
import eu.parcifal.plus.print.output.Warning.Level;
import eu.parcifal.plus.thread.Runner;

/**
 * Listens for incoming connection on a port and assigns each connected client
 * to a responder.
 * 
 * @author Michaël van de Weerd
 */
public abstract class PortListener extends Runner {
	private static final String MESSAGE_LISTEN_INIT = "started listening for requests on port %1$s";

	private static final String MESSAGE_LISTEN_FIN = "stopped listening for requests on port %1$s";

	/**
	 * The port at which the current port listener is listening.
	 */
	private int port;

	/**
	 * The socket used while listening for connections.
	 */
	private ServerSocket socket;

	/**
	 * Construct a port listener listening for connections at the specified
	 * port.
	 * 
	 * @param port
	 *            The port at which the port listener should listen.
	 */
	public PortListener(int port) {
		this.port = port;
	}

	@Override
	protected final void initialise() {
		try {
			this.socket = new ServerSocket(this.port);

			Console.log(MESSAGE_LISTEN_INIT, this.port);
		} catch (IOException ioe) {
			Console.warn(Level.FATAL, ioe.getMessage());

			this.stop();
		}
	}

	@Override
	protected final void act() {
		try {
			this.pool().get(this.socket.accept());
		} catch (IOException ioe) {
			Console.warn(Level.HIGH, ioe.getMessage());
		}
	}

	@Override
	protected final void finalise() {
		try {
			this.socket.close();

			Console.log(MESSAGE_LISTEN_FIN, this.port);
		} catch (IOException ioe) {
			Console.warn(Level.HIGH, ioe.getMessage());
		}
	}

	/**
	 * Return the pool of responders from which the current port listener can
	 * retrieve responders for incoming connections.
	 * 
	 * @return The pool of responders.
	 */
	protected abstract Pool<Exchanger> pool();

}
