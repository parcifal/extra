package eu.parcifal.extra.logic;

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
	 * Attempt to find a route matching the specified path. Multiple routes can
	 * be followed if there are multiple matches.
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
	public void route(String path, Object... data) throws RouteNotFoundException {
		boolean succes = false;

		for (Route route : this.routes) {
			succes = succes || route.attempt(path, data);
		}

		if (!succes) {
			throw new RouteNotFoundException(String.format(MESSAGE_ROUTE_NOT_FOUND, path), path);
		}
	}

}
