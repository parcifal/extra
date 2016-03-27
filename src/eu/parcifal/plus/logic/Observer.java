package eu.parcifal.plus.logic;

public interface Observer {

	public void observe(Observable source, Object... args);

}
