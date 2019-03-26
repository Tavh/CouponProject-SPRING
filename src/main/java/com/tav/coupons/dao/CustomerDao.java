package com.tav.coupons.dao; 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tav.coupons.entities.*;
import com.tav.coupons.enums.ErrorType;
import com.tav.coupons.exceptions.ApplicationException;

@Repository
public class CustomerDao {

	@PersistenceContext(unitName="CouponSpringUnit")
	private EntityManager entityManager;

	// -----------------------------Create a new customer-------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public long createCustomer (CustomerEntity customer) throws ApplicationException {

		entityManager.persist(customer);

		return customer.getUserLoginDetails().getId();
	}

	// ----------------------------Remove a customer------------------------------

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeCustomer (long id) throws ApplicationException{

		CustomerEntity customer = entityManager.find(CustomerEntity.class, id);

		entityManager.remove(customer);
	}


	// -------------------------------Update a customer---------------------------

	// Updates a customer by "Overriding" it with the new version of the customer
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateCustomer (CustomerEntity customer) throws ApplicationException {

		entityManager.merge(customer);
	}

	// --------------------------------Getters-------------------------------------

	// If the customer doesn't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomerEntity getCustomer (long userId) throws ApplicationException {

		Query query = entityManager.createQuery("FROM CustomerEntity customer WHERE customer.userLoginDetails.id=:id").setParameter("id", userId);

		CustomerEntity customer; 

		List results = query.getResultList();

		if(results.isEmpty()) {
			customer = null;
		} else {
			customer = (CustomerEntity) query.getSingleResult();
		}

		return customer;
	}


	// If the customer doesn't exist, returns null
	@Transactional(propagation=Propagation.REQUIRED)
	public CustomerEntity getCustomer (String email) throws ApplicationException {

		Query query = entityManager.createQuery("FROM CustomerEntity customer WHERE customer.userLoginDetails.email=:email").setParameter("email", email);

		CustomerEntity customer; 

		List results = query.getResultList();

		if(results.isEmpty()) {
			customer = null;
		} else {
			customer = (CustomerEntity) query.getSingleResult();
		}

		return customer;
	}

	// Gets all the customers that are recorded in the database
	@Transactional(propagation = Propagation.REQUIRED)
	public List<CustomerEntity> getAllCustomers () throws ApplicationException {

		Query query = entityManager.createQuery("FROM CustomerEntity");

		@SuppressWarnings("unchecked")
		List<CustomerEntity> allCustomers = query.getResultList();

		return allCustomers;
	}
}
