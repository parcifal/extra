package eu.parcifal.plus.thread;

import eu.parcifal.plus.print.Console;
import eu.parcifal.plus.print.output.Warning.Level;

/**
 * Call the intervalAct() method at an interval in a thread.
 * 
 * @author Michaël van de Weerd
 */
public abstract class IntervalRunner extends Runner {
	/**
	 * The default act period.
	 */
	private static final long DEFAULT_ACT_PERIOD = 50;

	/**
	 * The warning message for when the execution time of the act method is
	 * greater than the act period.
	 */
	private static final String WARNING_OVERTIME = "the execution time of method intervalAct() took %1$sms longer than the current act period of %2$sms, consider raising its value using actPeriod(long)";

	/**
	 * The amount of time between each call of the intervalAct() method in
	 * milliseconds.
	 */
	private long actPeriod = DEFAULT_ACT_PERIOD;

	/**
	 * Set the act period of the current interval runner to the specified length
	 * in milliseconds.
	 * 
	 * @param length
	 *            The new length of the act period of the current interval
	 *            runner in milliseconds.
	 */
	public final void actPeriod(long length) {
		this.actPeriod = length;
	}

	@Override
	protected final void act() {
		long start = System.currentTimeMillis();

		this.intervalAct();

		long sleep = this.actPeriod - (System.currentTimeMillis() - start);

		if (sleep >= 0) {
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException ie) {
				Console.warn(Level.HIGH, ie.getMessage());
			}
		} else {
			Console.warn(Level.LOW, WARNING_OVERTIME, sleep, this.actPeriod);
		}
	}

	/**
	 * Perform actions during the run of the current interval runner at an
	 * interval of the length of the act period.
	 */
	abstract protected void intervalAct();

}
