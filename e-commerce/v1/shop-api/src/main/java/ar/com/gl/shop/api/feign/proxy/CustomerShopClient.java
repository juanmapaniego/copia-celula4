package ar.com.gl.shop.api.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ar.com.gl.shop.api.feign.dto.CustomerDTO;

@FeignClient(value = "customer-shop")
public interface CustomerShopClient {

    // CRUD Customer
    @GetMapping(path = "/customershop/v1/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO findById(@PathVariable(name = "customerId") Long customerId);

    @GetMapping(path = "/customershop/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<CustomerDTO> findAll();

    @PostMapping(path = "/customershop/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO);

    @DeleteMapping(path = "/customershop/v1/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable(name = "customerId") Long customerId);

    @PutMapping(path = "/customershop/v1/customers/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO update(@PathVariable(name = "customerId") Long customerId,
	    @RequestBody CustomerDTO updateCustomerDTO);

}
