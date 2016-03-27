package eu.parcifal.plus.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.parcifal.plus.print.Console;

/**
 * A route contains several cases against which a string of data can be tested.
 * When a match occurs a handler associated with the route can be executed.
 * 
 * @author Michaël van de Weerd
 */
public class Route {
	/**
	 * Matches an empty path (except any amount of forward slashes at the
	 * beginning).
	 */
	public final static Pattern PATH_EMPTY_GET = Pattern.compile("^\\/*$");

	/**
	 * Matches an empty path (except any amount of forward slashes at the
	 * beginning or GET parameters at the end).
	 */
	public final static Pattern PATH_EMPTY = Pattern.compile(
			"^\\/*(?:(?:\\?[^:\\/\\?#\\[\\]@]*=[^:\\/\\?#\\[\\]@]*)(?:&[^:\\/\\?#\\[\\]@]*=[^:\\/\\?#\\[\\]@]*)*)?$");

	public final static Pattern HOST_LOCALHOST = Pattern.compile("localhost|127\\.0\\.0\\.1|::1");

	public static final Pattern CATCH_ALL = Pattern.compile(".*");

	/**
	 * The handler associated with the current route.
	 */
	private Executable handler;

	/**
	 * The cases in which the current route is valid.
	 */
	private Pattern[] cases;

	/**
	 * Construct a new route, valid in the specified cases and associated with
	 * the specified handler.
	 * 
	 * @param cases
	 *            The cases in which the current route is valid.
	 * @param handler
	 *            The handler associated with the current route.
	 */
	public Route(Executable handler, Pattern... cases) {
		this.handler = (Executable) handler;
		this.cases = cases;
	}

	public Route(Executable handler, String... cases) {
		this.handler = (Executable) handler;
		this.cases = new Pattern[cases.length];

		for (int i = 0; i < cases.length; i++) {
			this.cases[i] = Pattern.compile(cases[i]);
		}
	}

	/**
	 * Return true if the specified path matches at least one of the cases of
	 * the current route.
	 * 
	 * @param path
	 *            The path to be matched by one of the cases of the current
	 *            route.
	 * @return True if the specified path matches at least one of the cases of
	 *         the current route, otherwise false.
	 */
	public boolean follows(String path) {
		for (int i = 0; i < this.cases.length; i++) {
			Matcher matcher = this.cases[i].matcher(path);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Execute the handler associated with the current route, having the
	 * specified data as its parameter, if the specified path matches at least
	 * one of the cases of the current route.
	 * 
	 * @param path
	 *            The path currently being routed.
	 * @param data
	 *            The data send to the execute method of the handler associated
	 *            with the current route.
	 * @return True if the specified path matches at least one of the cases of
	 *         the current route, otherwise false.
	 */
	public Object follow(Object... data) {
		return this.handler.execute(data);
	}

}
