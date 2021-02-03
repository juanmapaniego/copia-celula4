package ar.com.gl.shopCustomerShop.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.gl.shopCustomerShop.customer.dtos.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "CU_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CU_FIRSTNAME")
    private String firstName;

    @Column(name = "CU_LASTNAME")
    private String lastName;

    @Column(name = "CU_EMAIL")
    private String email;

    @Column(name = "CU_STATUS")
    private String status;

    public static Customer createFromDTO(CustomerDTO customer) {
	return Customer.builder().id(customer.getId()).firstName(customer.getFirstName())
		.lastName(customer.getLastName()).email(customer.getEmail()).status("activo").build();
    }

}
