package eu.parcifal.extra.parsing.old;

public interface Parser {

	public String parse(String plain, int flags);

	public default String parse(String plain) {
		return this.parse(plain, 0);
	}

}
