package eu.parcifal.plus.data.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A list node contains a single object as its content and a list of other nodes
 * (children).
 * 
 * @author Michaël van de Weerd
 *
 * @param <T>
 *            The type of the content object, contained within each list node.
 */
public class ListNode<T> extends ArrayList<ListNode<T>> implements Node {
	/**
	 * The serial version unique identifier.
	 */
	private static final long serialVersionUID = -8272009479790418876L;

	/**
	 * The content of the current node.
	 */
	private T content;

	/**
	 * Construct a new node.
	 * 
	 * @param content
	 *            The content of the new node.
	 */
	public ListNode(T content) {
		this.content = content;
	}

	/**
	 * Set the content of the current node.
	 * 
	 * @param content
	 *            The content of the current node.
	 */
	public void setContent(T content) {
		this.content = content;
	}

	/**
	 * Return the content of the current node.
	 * 
	 * @return The content of the current node.
	 */
	public T getContent() {
		return this.content;
	}

	@Override
	public Collection<Node> children() {
		return (Collection<Node>) super.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ListNode<?>)
			return ((ListNode<?>) o).getContent().equals(this.content);

		return false;
	}

	@Override
	public String toString() {
		return this.content.toString();
	}

}
