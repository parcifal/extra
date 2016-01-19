package eu.parcifal.extra.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import eu.parcifal.extra.logic.Poolable;
import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;

public abstract class Responder extends Poolable implements Runnable {

	private static final long DEFAULT_PAUSE_PERIOD = 25;

	private long pausePeriod = DEFAULT_PAUSE_PERIOD;

	private Socket client;

	private int runCycle;

	private boolean running = false;

	private boolean paused = false;

	public final boolean paused() {
		return this.paused;
	}

	public final boolean running() {
		return this.running;
	}

	public final int runCycle() {
		return this.runCycle;
	}

	public final void pause() {
		this.paused = true;
	}

	public final void resume() {
		this.paused = false;
	}

	public final void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		this.running = true;

		while (this.running) {
			this.runCycle++;

			if (!this.paused) {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

					String request = "", line;

					while ((line = in.readLine()) != null) {
						request = request + line;
					}

					client.getOutputStream().write(this.response(request));
				} catch (IOException ioe) {
					Console.warn(Level.HIGH, ioe.getMessage());
				}

				this.notify(this);

				this.pause();
			} else {
				try {
					Thread.sleep(this.pausePeriod);
				} catch (InterruptedException ie) {
					Console.warn(Level.HIGH, ie.getMessage());
				}
			}
		}

		try {
			this.client.close();
		} catch (IOException ioe) {
			Console.warn(Level.HIGH, ioe.getMessage());
		}
	}

	@Override
	public void initialise(Object... args) {
		if (!(args[0] instanceof Socket)) {
			throw new IllegalArgumentException();
		} else {
			this.client = (Socket) args[0];
			this.resume();
		}
	}

	protected abstract byte[] response(String request);

}
