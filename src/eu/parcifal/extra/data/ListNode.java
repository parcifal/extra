package eu.parcifal.extra.data;

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
public class ListNode<T> extends ArrayList<ListNode<T>> {
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

	/**
	 * Return whether or not the content of the current node or the content of
	 * any of its children is equal to the specified object.
	 * 
	 * @param o
	 *            The object to which the content of the current node or the
	 *            content of its children is compared using the equals(Object)
	 *            method.
	 * @return True if the content of the current node of the content of any of
	 *         its children is equal to the specified object, otherwise false.
	 */
	@Override
	public boolean contains(Object o) {
		if (this.content.equals(o))
			return true;

		for (ListNode<T> child : this)
			if (child.contains(o))
				return true;

		return false;
	}

	/**
	 * Return whether or not every object in the specified collection is the
	 * content of the current node or the content of any of its children.
	 * 
	 * @param c
	 *            The collection containing objects to which the content of the
	 *            current node or the content of its children is compared using
	 *            the equals(Object) method.
	 * @return True if every object in the specified collection is the content
	 *         of the current node or the content of any of its children,
	 *         otherwise false.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!this.contains(o))
				return false;

		return true;
	}

	@Override
	public boolean remove(Object o) {
		for (ListNode<T> child : this)
			if (child.equals(o))
				return super.remove(child);
			else if (child.remove(o))
				return true;

		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;

		for (ListNode<T> child : this)
			changed &= remove(child);

		return changed;
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
