package eu.parcifal.extra.logic;

public interface Observer {

	public void notify(Observable source, Object... args);

}
