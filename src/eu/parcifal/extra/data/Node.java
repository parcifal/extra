package eu.parcifal.extra.data;

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
	public int height();

	/**
	 * Return the degree of the current node, i.e. the amount of child nodes.
	 * 
	 * @return The degree of the current node.
	 */
	public int degree();

	/**
	 * Return whether or not the current node is a leaf, i.e. does not have a
	 * child nodes.
	 * 
	 * @return True if the current node is a leaf, otherwise false.
	 */
	public boolean isLeaf();

	/**
	 * Return a collection of child nodes of the current node.
	 * 
	 * @return A collection of child nodes of the current node.
	 */
	public Collection<Node> getChildren();

}
