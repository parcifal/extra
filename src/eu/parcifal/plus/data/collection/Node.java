package eu.parcifal.plus.data.collection;

import java.util.Collection;

/**
 * Connected to other nodes (children) within a tree shaped structure.
 * 
 * @author Michaël van de Weerd
 */
public interface Node {
	/**
	 * Return the height of the current node, i.e. the largest amount of node
	 * between the current node and a leaf in the child nodes.
	 * 
	 * @return The height of the current node.
	 */
	public default int height() {
		int height = 0;

		for (Node child : this.children()) {
			if (child.height() > height) {
				height = child.height();
			}
		}

		return height;
	}

	/**
	 * Return the degree of the current node, i.e. the amount of child nodes.
	 * 
	 * @return The degree of the current node.
	 */
	public default int degree() {
		return this.children().size();
	}

	/**
	 * Return whether or not the current node is a leaf, i.e. does not have a
	 * child nodes.
	 * 
	 * @return True if the current node is a leaf, otherwise false.
	 */
	public default boolean isLeaf() {
		return this.children().isEmpty();
	}

	/**
	 * Return a collection of child nodes of the current node.
	 * 
	 * @return A collection of child nodes of the current node.
	 */
	public Collection<Node> children();

}
