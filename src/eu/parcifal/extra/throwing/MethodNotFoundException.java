package eu.parcifal.extra.throwing;

public class MethodNotFoundException extends Exception {

	private static final long serialVersionUID = 5542342208692428958L;

	private String name;
	private Object[] args;

	public MethodNotFoundException(String name, Object... args) {
		this.name = name;
		this.args = args;
	}

	public MethodNotFoundException(String name) {
		this.name = name;
	}

	public String name() {
		return this.name;
	}

	public Object[] args() {
		return this.args;
	}

}
