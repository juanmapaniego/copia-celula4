package ar.com.gl.shopCustomerShop.customer.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ar.com.gl.shopCustomerShop.customer.exceptions.EmptyListException;
import ar.com.gl.shopCustomerShop.customer.exceptions.WrongValueException;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(EmptyListException.class)
  public void handleEmptyListException(EmptyListException exception) {

    // log?
    System.out.println(exception.getMessage());
  }

  @ExceptionHandler(WrongValueException.class)
  public ResponseEntity<Object> handleWrongValueException(
      WrongValueException exception, WebRequest request) {
    // Log "wrong value provided"??
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
