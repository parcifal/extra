package eu.parcifal.extra.logic;

/**
 * Thrown by a router when a path cannot be matched to a route.
 * 
 * @author Michaël van de Weerd
 */
public class RouteNotFoundException extends Exception {
	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = -1862198359483981052L;

	/**
	 * The path for which no matching route can be found.
	 */
	private String path;

	/**
	 * Construct a route not found exception containing the specified message
	 * and HTTP exchange.
	 * 
	 * @param message
	 *            The message of the new route not found exception.
	 * @param path
	 *            The path for which no matching route can be found.
	 */
	public RouteNotFoundException(String message, String path) {
		super(message);

		this.path = path;
	}

	/**
	 * Get the path for which no matching route can be found.
	 * 
	 * @return The path for which no matching route can be found.
	 */
	public String getPath() {
		return this.path;
	}

}
