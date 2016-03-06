package eu.parcifal.plus.logic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Contains and handles one or more routes.
 * 
 * @author Michaël van de Weerd
 */
public class Router {
	
	public final static Router EMPTY = new Router();
	
	/**
	 * The message to be used in the case of a route not found exception.
	 */
	private final static String MESSAGE_ROUTE_NOT_FOUND = "no route matching path \"%1$s\" has been found";

	/**
	 * The routes of the current router.
	 */
	private Collection<Route> routes = new ArrayList<Route>();

	/**
	 * Construct a routes containing one or more routes.
	 * 
	 * @param routes
	 *            The routes of the new router.
	 */
	@SafeVarargs
	public Router(Route... routes) {
		for(Route route : routes) {
			this.routes.add(route);
		}
	}
	
	public void add(Route route) {
		this.routes.add(route);
	}

	/**
	 * Attempt to find a route matching the specified path.
	 * 
	 * @param path
	 *            The path to be attempted to follow.
	 * @param data
	 *            The data send to the execute method of the handler associated
	 *            with the matching routes.
	 * @throws RouteNotFoundException
	 *             If no route matching the URI path in the specified HTTP
	 *             exchange has been found.
	 */
	public Object route(String path, Object... data) throws RouteNotFoundException {
		for (Route route : this.routes) {
			if (route.follows(path)) {
				return route.follow(data);
			}
		}

		throw new RouteNotFoundException(String.format(MESSAGE_ROUTE_NOT_FOUND, path), path);
	}

}
