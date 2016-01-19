package eu.parcifal.extra.logic;

import java.util.Stack;

public abstract class Pool<T extends Poolable> implements Observer {

	private Stack<Poolable> idle = new Stack<Poolable>();

	public final Poolable get(Object... args) {
		if (this.idle.isEmpty()) {
			return this.instantiate(args);
		} else {
			return this.idle.pop();
		}
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
