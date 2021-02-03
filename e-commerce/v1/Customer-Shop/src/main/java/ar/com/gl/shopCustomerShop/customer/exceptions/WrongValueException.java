package ar.com.gl.shopCustomerShop.customer.exceptions;

public class WrongValueException extends RuntimeException {

  /** */
  private static final long serialVersionUID = 1L;

  public WrongValueException() {
    super();
  }

  public WrongValueException(String message) {
    super(message);
  }
}
