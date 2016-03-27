package eu.parcifal.plus.logic;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {

	private Collection<Observer> observers = new ArrayList<Observer>();

	public final void observe(Observer observer) {
		this.observers.add(observer);
	}

	protected final void notifyObservers(Object... args) {
		for (Observer observer : this.observers) {
			observer.observe(this, args);
		}
	}

}
