package eu.parcifal.extra.debug;

import java.util.ArrayList;
import java.util.Collection;

public class Console {
	private static Collection<Output> output = new ArrayList<Output>();

	/**
	 * Return a clone of the output that has been passed through the console.
	 * 
	 * @return A clone of the output that has been passed through the console.
	 */
	public Collection<Output> getOutput() {
		Collection<Output> output = new ArrayList<Output>();

		for (Output o : Console.output)
			output.add(o.clone());

		return output;
	}

	public static void log(Object source, Object content) {
		out(new Log(source.getClass(), content));
	}

	public static void warn(Object source, Object content, Warning.Level level) {
		out(new Warning(source.getClass(), content, level));
	}

	private static void out(Output output) {
		output.print();

		Console.output.add(output);
	}
}
