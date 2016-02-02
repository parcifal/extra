package eu.parcifal.plus.logic;

public interface Observer {

	public void notify(Observable source, Object... args);

}
