package eu.parcifal.plus.logic;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public abstract class Pool implements Observer {

	private final static int DEFAULT_MINIMUM = 8;

	private Stack<Poolable> idle = new Stack<Poolable>();

	private Queue<Object[]> tasks = new PriorityQueue<Object[]>();

	private int minimum;

	public Pool() {
		this(DEFAULT_MINIMUM);
	}

	public Pool(int minimum) {
		this.minimum = minimum;
		this.initialise();
	}

	private void initialise() {
		for (int i = 0; i < this.minimum; i++) {
			Poolable poolable = this.instantiate();

			poolable.observe(this);

			this.idle.push(poolable);
		}
	}

	private void process() {
		while (!this.tasks.isEmpty() && !this.idle.isEmpty()) {
			Poolable poolable = this.idle.pop();

			synchronized (poolable.monitor()) {
				poolable.initialise(this.tasks.remove());
				poolable.monitor().notify();
			}
		}
	}

	public void task(Object... arguments) {
		this.tasks.add(arguments);

		this.process();
	}

	@Override
	public void observe(Observable source, Object... arguments) {
		if (source instanceof Poolable) {
			this.idle.push((Poolable) source);
		}

		this.process();
	}

	protected abstract Poolable instantiate(Object... args);

}
