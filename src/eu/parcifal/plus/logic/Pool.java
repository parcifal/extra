package eu.parcifal.plus.logic;

import java.util.Stack;

public abstract class Pool<T extends Poolable> implements Observer {

	private Stack<Poolable> idle = new Stack<Poolable>();

	public final Poolable get(Object... args) {
		Poolable poolable = null;

		if (this.idle.isEmpty()) {
			poolable = this.instantiate(args);
		} else {
			poolable = this.idle.pop();

			poolable.initialise(args);
		}

		return poolable;
	}

	protected abstract T instantiate(Object... args);

	@Override
	public final void notify(Observable source, Object... args) {
		if (!(source instanceof Poolable)) {
			throw new IllegalArgumentException();
		} else {
			this.idle.push((Poolable) source);
		}
	}

}
