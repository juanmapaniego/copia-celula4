package ar.com.gl.shopCustomerShop.customer.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.shopCustomerShop.customer.controller.CustomerController;
import ar.com.gl.shopCustomerShop.customer.dtos.CustomerDTO;
import ar.com.gl.shopCustomerShop.customer.exceptions.WrongValueException;
import ar.com.gl.shopCustomerShop.customer.services.CustomerService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "/customers")
@Api(tags = {"Customers"})
public class CustomerControllerImpl implements CustomerController {

  @Autowired private CustomerService service;

  @Override
  @GetMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDTO> findById(@PathVariable(name = "customerId") Long customerId) {

    return new ResponseEntity<>(service.getCustomerById(customerId), HttpStatus.OK);
  }

  @Override
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<CustomerDTO>> findAll() {
    List<CustomerDTO> response = null;

    response = service.getAllCustomers();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
    return new ResponseEntity<>(service.createCustomer(customerDTO), HttpStatus.CREATED);
  }

  @Override
  @DeleteMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable(name = "customerId") Long customerId) {

    if (customerId <= 0l) throw new WrongValueException();

    service.deleteCustomerById(customerId);
    ;
    // service.logicalDeleteProduct(ProductDTO.builder().id(productId).build());

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @PutMapping(path = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CustomerDTO> update(
      @PathVariable(name = "customerId") Long customerId,
      @RequestBody CustomerDTO updateCustomerDTO) {
    if (customerId <= 0l) throw new WrongValueException();

    return new ResponseEntity<>(service.updateCustomer(updateCustomerDTO), HttpStatus.OK);
  }
}
