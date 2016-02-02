package eu.parcifal.plus.logic;

public abstract class Poolable extends Observable {

	public abstract void initialise(Object... args);

	private Object manager = new Object();

	public Object manager() {
		return this.manager;
	}

}
