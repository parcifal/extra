package eu.parcifal.extra.logic;

import eu.parcifal.extra.throwing.RouteNotFoundException;

/**
 * Contains and handles one or more routes.
 * 
 * @author Michaël van de Weerd
 */
public class Router {
	/**
	 * The message to be used in the case of a route not found exception.
	 */
	private final static String MESSAGE_ROUTE_NOT_FOUND = "no route matching path \"%1s\" has been found";

	/**
	 * The routes of the current router.
	 */
	private Route[] routes;

	/**
	 * Construct a routes containing one or more routes.
	 * 
	 * @param routes
	 *            The routes of the new router.
	 */
	public Router(Route... routes) {
		this.routes = routes;
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
				return route.follow();
			}
		}

		throw new RouteNotFoundException(String.format(MESSAGE_ROUTE_NOT_FOUND, path), path);
	}

}
