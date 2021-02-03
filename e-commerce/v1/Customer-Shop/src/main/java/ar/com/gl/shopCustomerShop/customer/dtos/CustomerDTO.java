package ar.com.gl.shopCustomerShop.customer.dtos;

import ar.com.gl.shopCustomerShop.customer.model.Customer;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Customer", description = "Object that represent a customer")
public class CustomerDTO {
	private Long id;
	private String firstName;
	private String lastName;

	private String email;

	public static CustomerDTO createFromEntity(Customer customer) {
		return CustomerDTO.builder().id(customer.getId()).firstName(customer.getFirstName())
				.lastName(customer.getLastName()).email(customer.getEmail()).build();
	}

}
