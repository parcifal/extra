package eu.parcifal.extra.thread;

import eu.parcifal.extra.debug.Console;
import eu.parcifal.extra.debug.Warning;

/**
 * A partial implementation of the runnable interface, providing three methods
 * to initiate, act every cycle and finish.
 * 
 * @author Michaël van de Weerd
 */
public abstract class Runner implements Runnable {
	/**
	 * The default length of the act period.
	 */
	private static final long DEFAULT_ACT_PERIOD = 50;

	/**
	 * The default length of the pause period.
	 */
	private static final long DEFAULT_PAUSE_PERIOD = 25;

	/**
	 * The warning message for when the execution time of the act method is
	 * greater than the act period.
	 */
	private static final String WARNING_OVERTIME = "the execution time of method act() took %1$sms longer than the current act period of %2$sms, consider raising its value using setActPeriod(long)";

	/**
	 * The warning message for when the runner has been interrupted while
	 * running.
	 */
	private static final String WARNING_INTERRUPTED = "the runner has been interrupted by %1$s and will stop running";

	/**
	 * The act period of the current runner.
	 */
	private long actPeriod = DEFAULT_ACT_PERIOD;

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
	 * Set the act period of the current runner to the specified length.
	 * 
	 * @param length
	 *            The new length of the act period of the current runner.
	 */
	public void setActPeriod(long length) {
		this.actPeriod = length;
	}

	/**
	 * Set the pause period of the current runner to the specified length.
	 * 
	 * @param length
	 *            The new length of the pause period of the current runner.
	 */
	public void setPausePeriod(long length) {
		this.pausePeriod = length;
	}

	/**
	 * Return whether or not the current runner is paused.
	 * 
	 * @return True if the current runner is paused, otherwise false.
	 */
	public boolean isPaused() {
		return this.paused;
	}

	/**
	 * Return whether or not the current runner is running.
	 * 
	 * @return True if the current runner is running, otherwise false.
	 */
	public boolean isRunning() {
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

			this.initiate();

			// used during this run for the calculation of the remaining time of
			// the act period
			long start, sleep;

			while (this.running) {
				start = System.currentTimeMillis();

				this.runCycle++;

				if (!this.paused) {
					this.act();
					this.actCycle++;

					sleep = actPeriod - (System.currentTimeMillis() - start);
				} else {
					sleep = pausePeriod - (System.currentTimeMillis() - start);
				}

				if (sleep >= 0) {
					Thread.sleep(sleep);
				} else {
					Console.warn(this, String.format(WARNING_OVERTIME, -sleep, this.actPeriod), Warning.Level.HIGH);
				}
			}
		} catch (InterruptedException exception) {
			Console.warn(this, String.format(WARNING_INTERRUPTED, exception.getCause()), Warning.Level.SEV);

			this.stop();
		} finally {
			this.finish();
		}
	}

	/**
	 * Perform actions once at the beginning of the run of the current runner.
	 */
	protected void initiate() {
		// implementation optional
	}

	/**
	 * Perform actions during the run of the current runner at an interval of
	 * the length of the act period.
	 */
	abstract protected void act();

	/**
	 * Perform actions once at the end of the run of the current runner.
	 */
	protected void finish() {
		// implementation optional
	}

}
