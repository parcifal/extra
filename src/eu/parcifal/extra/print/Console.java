package eu.parcifal.extra.print;

import java.util.ArrayList;
import java.util.Collection;

import eu.parcifal.extra.print.output.Debug;
import eu.parcifal.extra.print.output.Log;
import eu.parcifal.extra.print.output.Printable;
import eu.parcifal.extra.print.output.Warning;

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

	public static void log(Object content) {
		print(new Log(content));
	}

	public static void log(String content, Object... args) {
		print(new Log(String.format(content, args)));
	}

	public static void debug(Object content) {
		print(new Debug(content));
	}

	public static void debug(String content, Object... args) {
		print(new Debug(String.format(content, args)));
	}

	public static void warn(Warning.Level level, Object content) {
		print(new Warning(content, level));
	}

	public static void warn(Warning.Level level, Object content, Object... args) {
		print(new Warning(String.format(content.toString(), args), level));
	}

	private static void print(Printable printable) {
		Console.output.add(printable);

		printable.print(Console.debug);
	}

	public static void printDebug(boolean debug) {
		Console.debug = debug;
	}
}
