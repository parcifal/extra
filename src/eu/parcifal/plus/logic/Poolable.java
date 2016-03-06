package eu.parcifal.plus.logic;

public abstract class Poolable extends Observable {

	public abstract void initialise(Object... args);

	private Object monitor = new Object();

	public Object monitor() {
		return this.monitor;
	}

}
