package eu.parcifal.extra.net;

import java.io.IOException;
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

	protected abstract byte[] response(byte[] request);

}
