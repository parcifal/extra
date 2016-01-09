package eu.parcifal.extra.print;

import java.util.ArrayList;
import java.util.Collection;

public class Console {
	private static Collection<Printable> output = new ArrayList<Printable>();

	private static boolean debug = false;

	public Collection<Printable> getOutput() {
		Collection<Printable> printables = new ArrayList<Printable>();

		for (Printable o : Console.output) {
			printables.add(o.clone());
		}

		return printables;
	}

	public static void log(Object source, Object content) {
		Log log = new Log(source.getClass(), content, false);

		print(log);
	}

	public static void warn(Object source, Object content, Warning.Level level) {
		Warning warning = new Warning(source.getClass(), content, level, false);

		print(warning);
	}

	public static void debugLog(Object source, Object content) {
		Log log = new Log(source.getClass(), content, true);

		print(log);
	}

	public static void debugWarn(Object source, Object content, Warning.Level level) {
		Warning warning = new Warning(source.getClass(), content, level, true);

		print(warning);
	}

	private static void print(Printable printable) {
		Console.output.add(printable);

		printable.print(Console.debug);
	}
	
	public static void debug(boolean debug) {
		Console.debug = debug;
	}
}
