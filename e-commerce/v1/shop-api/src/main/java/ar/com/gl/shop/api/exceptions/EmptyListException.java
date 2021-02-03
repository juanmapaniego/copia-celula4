package ar.com.gl.shop.api.exceptions;

public class EmptyListException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

	public EmptyListException() {
		super();
		this.message = "";
	}

	public EmptyListException(String msg) {
		super(msg);
		this.message = msg;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
