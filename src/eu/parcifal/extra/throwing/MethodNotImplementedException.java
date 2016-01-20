package eu.parcifal.extra.throwing;

public class MethodNotImplementedException extends RuntimeException {

	private static final long serialVersionUID = -8994751165761895729L;

	private final static String STRING_FORMAT = "%1$s has no implementation";

	public MethodNotImplementedException() {
		super();
	}

	@Override
	public String getMessage() {
		return String.format(STRING_FORMAT, this.getStackTrace()[0].toString());
	}

}
