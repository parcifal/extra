package eu.parcifal.plus.data;

public class Point {
	/**
	 * The values of the current point in each dimension.
	 */
	private double[] values;

	/**
	 * Construct a new point containing coordinates.
	 * 
	 * @param values
	 *            The values of the current point at each dimension.
	 */
	public Point(double... values) {
		this.values = values;
	}

	/**
	 * Return the amount of dimensions in the current point.
	 * 
	 * @return The amount of dimensions in the current point.
	 */
	public int dimensions() {
		return this.values.length;
	}

	/**
	 * Return the value of the current point in the specified dimension.
	 * 
	 * @param dimension
	 *            The dimension in the current point of which the value must be
	 *            returned.
	 * @return The value of the current point in the specified dimension.
	 */
	public double get(int dimension) {
		if (dimension < 0 || dimension > this.dimensions())
			throw new IllegalArgumentException();
		else
			return this.values[dimension];
	}
	
	public double[] getAll() {
		return this.values;
	}

	/**
	 * Set the value of the current point in the specified dimension.
	 * 
	 * @param dimension
	 *            The dimension in which the value of the current point is
	 *            changed.
	 * @param value
	 *            The new value of the current point in the specified dimension.
	 */
	public void set(int dimension, double value) {
		if (dimension < 0 || dimension > this.dimensions())
			throw new IllegalArgumentException();
		else
			this.values[dimension] = value;
	}

	/**
	 * Set the values of the current point in all dimensions.
	 * 
	 * @param values 
	 */
	public void setAll(double... values) {
		if (values.length != this.dimensions())
			throw new IllegalArgumentException();
		else {
			for (int i = 0; i < values.length; i++)
				set(i, values[i]);
		}
	}

	/**
	 * Return the Euclidean distance between the specified point 1 and the
	 * specified point 2.
	 * 
	 * @param point1
	 *            The first point.
	 * @param point2
	 *            The second point.
	 * @return The Euclidean distance between the specified point 1 and the
	 *         specified point 2.
	 */
	static public double euclideanDistance(Point point1, Point point2) {
		double squareSum = 0;

		for (int i = 0; i < point1.dimensions(); i++)
			squareSum += Math.pow(point1.get(i) - point2.get(i), 2);

		return Math.sqrt(squareSum);
	}

	/**
	 * Return the Manhattan distance between the specified point 1 and the
	 * specified point 2.
	 * 
	 * @param point1
	 *            The first point.
	 * @param point2
	 *            The second point.
	 * @return The Manhattan distance between the specified point 1 the
	 *         specified point 2.
	 */
	static public double manhattanDistance(Point point1, Point point2) {
		double absoluteSum = 0;

		for (int i = 0; i < point1.dimensions(); i++)
			absoluteSum += Math.abs(point1.get(i) - point2.get(i));

		return absoluteSum;
	}

}
