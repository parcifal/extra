package eu.parcifal.extra.debug;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple console used to format output in a readable way.
 * 
 * @author Michaël van de Weerd
 */
public class Console {
	private static Collection<Printable> output = new ArrayList<Printable>();

	/**
	 * Return a clone of the output that has been passed through the console.
	 * 
	 * @return A clone of the output that has been passed through the console.
	 */
	public Collection<Printable> getOutput() {
		Collection<Printable> printables = new ArrayList<Printable>();

		for (Printable o : Console.output) {
			printables.add(o.clone());
		}

		return printables;
	}

	public static void log(Object source, Object content) {
		print(new Log(source.getClass(), content));
	}

	public static void warn(Object source, Object content, Warning.Level level) {
		print(new Warning(source.getClass(), content, level));
	}

	private static void print(Printable printable) {
		printable.print();

		Console.output.add(printable);
	}
}
