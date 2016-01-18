package eu.parcifal.extra.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;
import eu.parcifal.extra.thread.Runner;

public abstract class PortListener extends Runner {

	private final static String WARNING_SERVER_SOCKET = "the port listener could not open a socket at port %1s";

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
	protected void initialise() {
		try {
			this.socket = new ServerSocket(this.port);
		} catch (IOException ioe) {
			Console.warn(Level.FATAL, WARNING_SERVER_SOCKET, this.port);

			this.stop();
		}
	}

	@Override
	protected void act() {
		try {
			this.handle(this.socket.accept());
		} catch (IOException ioe) {
			Console.warn(Level.HIGH, ioe.getMessage());
		}
	}

	@Override
	protected void finalise() {
		try {
			this.socket.close();
		} catch (IOException ioe) {
			Console.warn(Level.HIGH, ioe.getMessage());
		}
	}

	/**
	 * Handle a connection made by a client.
	 * 
	 * @param client
	 *            The socket used to communicate with the client.
	 */
	abstract protected void handle(Socket client);

}
