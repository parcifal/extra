package eu.parcifal.extra.thread.test;

import eu.parcifal.extra.debug.Console;
import eu.parcifal.extra.thread.Runner;

public class RunnerTest extends Runner {

	private long delay = 25;

	@Override
	protected void initiate() {
		Console.log(this, "runner test initiate");
	}

	@Override
	synchronized protected void act() throws InterruptedException {
		if (this.actCycle() < 10) {
			Console.log(this, String.format("runner test act cycle %1$s, delay %2$s", this.actCycle(), this.delay));

			this.wait(delay);
		} else {
			this.stop();
		}

		this.delay += 5;
	}

	@Override
	protected void finish() {
		Console.log(this, "runner test finish");
	}

	public static void main(String[] args) {
		new Thread(new RunnerTest()).start();
	}

}
