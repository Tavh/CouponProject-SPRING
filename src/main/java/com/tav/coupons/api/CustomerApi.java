package com.tav.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tav.coupons.entities.CustomerEntity;
import com.tav.coupons.exceptions.ApplicationException;
import com.tav.coupons.logic.CustomerController;
import com.tav.coupons.logic.UserLoginDetailsController;

@RestController
@RequestMapping(value = "/loggedin/customers")
public class CustomerApi {

	@Autowired
	private CustomerController customerController;
	@Autowired
	private UserLoginDetailsController userLoginDetailsController;

	// ------------------------------------Constructor---------------------------------

	public CustomerApi () {
		this.customerController = new CustomerController();
		this.userLoginDetailsController = new UserLoginDetailsController();
	}

	// ------------------------------------Creates a new customer---------------------------------

	@PostMapping("/no-login-required")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createCustomer (@RequestBody CustomerEntity customer) throws ApplicationException {

		// If any of the methods throw an exception, a rollback occurs
		// ????? not sure it's alright to put this much logic on api
		userLoginDetailsController.createUserLoginDetails(customer.getUserLoginDetails());
		customerController.createCustomer(customer);
	}
	
	// ------------------------------------Removes a customer---------------------------------

	@DeleteMapping("/{id}")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeCustomer (@PathVariable long id) throws ApplicationException {

		// CascadeType.REMOVE doesn't work on @OneToOne
		// If any of the methods throw an exception, a rollback occurs
		// ????? not sure it's alright to put this much logic on api
		customerController.removeCustomer(id);
		userLoginDetailsController.removeUserLoginDetails(id);

	}
	
	// ------------------------------Update user login details---------------------------------

	@PutMapping
	public void updateCustomer (@RequestBody CustomerEntity customer) throws ApplicationException {

		customerController.updateCustomer(customer);
	}
	
	// ------------------------------------Getters---------------------------------

	@GetMapping("/{id}")
	public CustomerEntity getCustomer (@PathVariable long id) throws ApplicationException {
		return customerController.getCustomer(id);
	}

	@GetMapping("/by-email")
	public CustomerEntity getCustomer (@RequestParam String email) throws ApplicationException {
		return customerController.getCustomer(email);
	}

	@GetMapping("all-customers")
	public List <CustomerEntity> getAllCustomer () throws ApplicationException {

		return customerController.getAllCustomers();
	}

}
