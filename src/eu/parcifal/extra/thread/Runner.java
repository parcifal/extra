package eu.parcifal.extra.thread;

import eu.parcifal.extra.print.Console;
import eu.parcifal.extra.print.output.Warning.Level;
import eu.parcifal.extra.throwing.MethodNotImplementedException;

/**
 * A partial implementation of the runnable interface, providing three methods
 * to initialise, act every cycle and finalise.
 * 
 * @author Michaël van de Weerd
 */
public abstract class Runner implements Runnable {
	/**
	 * The default length of the pause period.
	 */
	private static final long DEFAULT_PAUSE_PERIOD = 25;
	/**
	 * The warning message for when the runner has been interrupted while
	 * running.
	 */
	private static final String WARNING_INTERRUPTED = "the runner has been interrupted by %1$s and will stop running";

	/**
	 * The pause period of the current runner.
	 */
	private long pausePeriod = DEFAULT_PAUSE_PERIOD;

	/**
	 * Indicates whether or not the current runner is running.
	 */
	private boolean running = false;

	/**
	 * Indicates whether or not the current runner is paused.
	 */
	private boolean paused = false;

	/**
	 * The amount of cycles of the current runner.
	 */
	private int runCycle = 0;

	/**
	 * The amount of times the act method has been executed.
	 */
	private int actCycle = 0;

	/**
	 * Set the pause period of the current runner to the specified length in milliseconds.
	 * 
	 * @param length
	 *            The new length of the pause period of the current runner in milliseconds.
	 */
	public void setPausePeriod(long length) {
		this.pausePeriod = length;
	}

	/**
	 * Return whether or not the current runner is paused.
	 * 
	 * @return True if the current runner is paused, otherwise false.
	 */
	public boolean paused() {
		return this.paused;
	}

	/**
	 * Return whether or not the current runner is running.
	 * 
	 * @return True if the current runner is running, otherwise false.
	 */
	public boolean running() {
		return this.running;
	}

	/**
	 * Return the amount of cycles of the current runner.
	 * 
	 * @return The amount of cycles of the current runner.
	 */
	public int runCycle() {
		return this.runCycle;
	}

	/**
	 * Return the amount of times the act method has been executed.
	 * 
	 * @return The amount of times the act method has been executed.
	 */
	public int actCycle() {
		return this.actCycle;
	}

	/**
	 * Pause executing the act method. Does nothing if the current runner is
	 * already paused.
	 */
	public void pause() {
		this.paused = true;
	}

	/**
	 * Resume executing the act method. Does nothing if the current runner is
	 * not paused.
	 */
	public void resume() {
		this.paused = false;
	}

	/**
	 * Permanently stop executing the act method. Does nothing if the current
	 * runner is not running.
	 */
	public void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		try {
			this.running = true;

			try {
				this.initialise();
			} catch (MethodNotImplementedException mnie) {
				Console.debug(mnie.getMessage());
			}

			while (this.running) {
				this.runCycle++;

				if (!this.paused) {
					this.act();
				} else {
					this.wait(pausePeriod);
				}
			}
		} catch (InterruptedException e) {
			Console.warn(Level.FATAL, WARNING_INTERRUPTED);
		} finally {
			try {
				this.finalise();
			} catch (MethodNotImplementedException mnie) {
				Console.debug(mnie.getMessage());
			}
		}
	}

	/**
	 * Perform actions once at the beginning of the run of the current runner.
	 */
	protected void initialise() {
		throw new MethodNotImplementedException();
	}

	/**
	 * Perform actions during the run of the current runner at an interval of
	 * the length of the act period.
	 */
	abstract protected void act();

	/**
	 * Perform actions once at the end of the run of the current runner.
	 */
	protected void finalise() {
		throw new MethodNotImplementedException();
	}

}
